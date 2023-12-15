package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.utils.LibGrafos.Grafo;
import com.ifes.trabalhodw.utils.ArvoreLib.ArvoreAVL;
import com.ifes.trabalhodw.exception.DependeciasCiclicasException;
import com.ifes.trabalhodw.exception.NotFoundErrorException;
import com.ifes.trabalhodw.model.dto.InputDto.TarefaInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.TarefaOutputDto;
import com.ifes.trabalhodw.model.entity.Tarefa;
import com.ifes.trabalhodw.repository.ITarefaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class TarefaApp implements IGenericApp<TarefaOutputDto, TarefaInputDto, UUID> {

    public class TarefaComparator implements Comparator<Tarefa> {
        @Override
        public int compare(Tarefa o1, Tarefa o2) {
            return o1.getId().compareTo(o2.getId());
        }
    }

    private final ModelMapper mapper;
    private final ITarefaRepository tarefaRepository;
    private final GrafoDependecia<Tarefa> tarefaGrafoDependecia;
    private final ArvoreAVL<Tarefa> tarefaArvore = new ArvoreAVL<>(new TarefaComparator());

    @Autowired
    public TarefaApp(ModelMapper mapper, ITarefaRepository tarefaRepository, GrafoDependecia<Tarefa> grafoDependecia) {
        this.mapper = mapper;
        this.tarefaRepository = tarefaRepository;
        this.tarefaGrafoDependecia = grafoDependecia;
        var tarefas = tarefaRepository.findAll();
        for(Tarefa tarefa : tarefas) {
            tarefaArvore.adicionar(tarefa);
        }

    }

    @Override
    public TarefaOutputDto create(TarefaInputDto entity) {
        Tarefa tarefa = mapper.map(entity, Tarefa.class);
        tarefa.getHistoriaDeUsuario().setId(entity.getHistoriaDeUsuarioId());
        tarefa = tarefaRepository.save(tarefa);

        return mapper.map(tarefaRepository.save(tarefa), TarefaOutputDto.class);
    }

    public List<TarefaOutputDto> createAll(List<TarefaInputDto> entities) {
        Type targetType = new TypeToken<List<TarefaOutputDto>>() {}.getType();
        Type targetInputType = new TypeToken<List<Tarefa>>() {}.getType();
        List<Tarefa> tarefas = this.mapper.map(entities, targetInputType);

        tarefas = this.tarefaRepository.saveAll(tarefas);
        return this.mapper.map(tarefas, targetType);

    }

    @Override
    public TarefaOutputDto getById(UUID uuid) {
        var tarefa = tarefaRepository.findById(uuid);
        if(tarefa.isEmpty()) {
             throw  new NotFoundErrorException("Tarefa não encontrada");
        }
        Tarefa tarefa1 = tarefa.get();
        return mapper.map(tarefa1, TarefaOutputDto.class);
    }

    @Override
    public void deleteById(UUID uuid) {

        Tarefa tarefa = new Tarefa();
        tarefa.setId(uuid);
        tarefaArvore.remover(tarefa);
        tarefaRepository.deleteById(uuid);
    }

    @Override
    public List<TarefaOutputDto> getAll() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        List<TarefaOutputDto> tarefaOutputDtos = new ArrayList<>();
        for(Tarefa tarefa : tarefas) {
            tarefaOutputDtos.add(mapper.map(tarefa, TarefaOutputDto.class));
        }
        return tarefaOutputDtos;
    }

    @Override
    public TarefaOutputDto update(UUID uuid, TarefaInputDto entity) {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(uuid);
        tarefa = this.tarefaArvore.pesquisar(tarefa);
        if(tarefa == null) {
            throw  new NotFoundErrorException("Tarefa não encontrada");
        }


        Tarefa tarefaNova = mapper.map(entity, Tarefa.class);
        ArrayList<Tarefa> dependencias = new ArrayList<>();
        for(UUID dependenciaId : entity.getDependencias()) {
            var dependencia = new Tarefa();
            dependencia.setId(dependenciaId);
            dependencias.add(dependencia);
        }
        tarefaNova.setDependencias(dependencias);

        tarefaNova = tarefaRepository.save(tarefaNova);

        return mapper.map(tarefaNova, TarefaOutputDto.class);
    }

    public List<TarefaOutputDto> getByHistoriaDeUsuario(UUID uuid) {
        List<Tarefa> tarefas = tarefaRepository.findAllByHistoriaDeUsuarioId(uuid);
        Type targetType = new TypeToken<List<TarefaOutputDto>>() {}.getType();
        return mapper.map(tarefas, targetType);
    }

    public List<TarefaOutputDto> getByProjeto(UUID uuid) {
        List<Tarefa> tarefas = tarefaRepository.findAllByProjetoId(uuid);
        Type targetType = new TypeToken<List<TarefaOutputDto>>() {}.getType();
        return mapper.map(tarefas, targetType);
    }

    public List<TarefaOutputDto> getOrdemExecucao(UUID uuid) {
        var tarefaOptional = tarefaRepository.findById(uuid);
        if(tarefaOptional.isEmpty()) {
            throw  new NotFoundErrorException("Tarefa não encontrada");
        }
        Tarefa tarefa = tarefaOptional.get();
        List<Tarefa> ordem = tarefaGrafoDependecia.recomendacao(tarefa, tarefa.getDependencias());
        Type targetType = new TypeToken<List<TarefaOutputDto>>() {}.getType();
        return mapper.map(ordem, targetType);
    }

    public boolean possuiCiclo(UUID uuid) {
        var tarefaOptional = tarefaRepository.findAllByProjetoId(uuid);
        Grafo<Tarefa> grafo = new Grafo<>();
        for(Tarefa tarefa : tarefaOptional) {
            for (Tarefa dependencia : tarefa.getDependencias()) {
                grafo.adicionarAresta(dependencia, tarefa, 1);
            }
        }

        return grafo.temCiclo();
    }
}
