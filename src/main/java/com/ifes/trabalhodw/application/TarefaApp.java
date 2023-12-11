package com.ifes.trabalhodw.application;

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

    @Autowired
    public TarefaApp(ModelMapper mapper, ITarefaRepository tarefaRepository) {
        this.mapper = mapper;
        this.tarefaRepository = tarefaRepository;
    }

    @Override
    public TarefaOutputDto create(TarefaInputDto entity) {
        Tarefa tarefa = mapper.map(entity, Tarefa.class);
        tarefa = tarefaRepository.save(tarefa);
        return mapper.map(tarefaRepository.save(tarefa), TarefaOutputDto.class);
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
        List<Tarefa> tarefas = tarefaRepository.findAll()
                .stream()
                .filter(tarefa -> tarefa.getHistoriaDeUsuario().getId().equals(uuid))
                .toList();
        Type targetType = new TypeToken<List<TarefaOutputDto>>() {}.getType();
        return mapper.map(tarefas, targetType);
    }

    public List<TarefaOutputDto> getByProjeto(UUID uuid) {
        List<Tarefa> tarefas = tarefaRepository.findAllByProjetoId(uuid);
        Type targetType = new TypeToken<List<TarefaOutputDto>>() {}.getType();
        return mapper.map(tarefas, targetType);
    }
}
