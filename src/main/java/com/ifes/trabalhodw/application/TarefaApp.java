package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.exception.DependeciasCiclicasException;
import com.ifes.trabalhodw.exception.NotFoundErrorException;
import com.ifes.trabalhodw.model.dto.InputDto.HistoriaDeUsuarioInputDto;
import com.ifes.trabalhodw.model.dto.InputDto.TarefaInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.HistoriaDeUsuarioOutputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.TarefaOutputDto;
import com.ifes.trabalhodw.model.entity.HistoriaDeUsuario;
import com.ifes.trabalhodw.model.entity.StatusTarefa;
import com.ifes.trabalhodw.model.entity.Tarefa;
import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import com.ifes.trabalhodw.model.entity.tipos.TipoTarefa;
import com.ifes.trabalhodw.repository.ITarefaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TarefaApp implements IGenericApp<TarefaOutputDto, TarefaInputDto, UUID> {

    private final ModelMapper mapper;
    private final ITarefaRepository tarefaRepository;
    private final GrafoDependecia<Tarefa> tarefaGrafoDependecia;

    @Autowired
    public TarefaApp(ModelMapper mapper, ITarefaRepository tarefaRepository, GrafoDependecia<Tarefa> grafoDependecia) {
        this.mapper = mapper;
        this.tarefaRepository = tarefaRepository;
        this.tarefaGrafoDependecia = grafoDependecia;
    }

    @Override
    public TarefaOutputDto create(TarefaInputDto entity) {
        Tarefa tarefa = mapper.map(entity, Tarefa.class);
        tarefa.getHistoriaDeUsuario().setId(entity.getHistoriaDeUsuarioId());
        tarefa = tarefaRepository.save(tarefa);

        // Verificar se a tarefa possui dependencia
        boolean temCiclo = this.tarefaGrafoDependecia.possuiDependencia(tarefa, tarefa.getDependencias());
        if(temCiclo){
            throw new DependeciasCiclicasException();
        }

        return mapper.map(tarefaRepository.save(tarefa), TarefaOutputDto.class);
    }

    public List<TarefaOutputDto> createAll(List<TarefaInputDto> entities) {
        Type targetType = new TypeToken<List<TarefaOutputDto>>() {}.getType();
        Type targetInputType = new TypeToken<List<Tarefa>>() {}.getType();
        List<Tarefa> tarefas = this.mapper.map(entities, targetInputType);

        for(Tarefa tarefa : tarefas) {
            boolean temCiclo = this.tarefaGrafoDependecia.possuiDependencia(tarefa, tarefa.getDependencias());
            if(temCiclo){
                throw new DependeciasCiclicasException();
            }
        }


        tarefas = this.tarefaRepository.saveAll(tarefas);
        return this.mapper.map(tarefas, targetType);

    }

    @Override
    public TarefaOutputDto getById(UUID uuid) {
        var tarefa = tarefaRepository.findById(uuid);
        if(tarefa.isEmpty()) {
             throw  new NotFoundErrorException("Tarefa não encontrada");
        }

        return mapper.map(tarefa.get(), TarefaOutputDto.class);
    }

    @Override
    public void deleteById(UUID uuid) {
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
        var tarefaAtual = tarefaRepository.findById(uuid);
        if(tarefaAtual.isEmpty()) {
            throw  new NotFoundErrorException("Tarefa não encontrada");
        }
        Tarefa tarefaNova = mapper.map(entity, Tarefa.class);
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
}
