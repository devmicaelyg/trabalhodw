package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.exception.NotFoundErrorException;
import com.ifes.trabalhodw.model.dto.InputDto.TipoTarefaInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.TipoTarefaOutputDto;
import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import com.ifes.trabalhodw.model.entity.tipos.TipoTarefa;
import com.ifes.trabalhodw.repository.ITipoTarefaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TipoTarefaApp implements IGenericApp<TipoTarefaOutputDto, TipoTarefaInputDto, UUID> {

    @Autowired
    private ITipoTarefaRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TipoTarefaOutputDto> getAll() {
        Type targetType = new TypeToken<List<TipoTarefaOutputDto>>() {}.getType();
        var listaTipos = repository.findAll();

        List<TipoTarefaOutputDto> listaDtos = modelMapper.map(listaTipos, targetType);
        return listaDtos;
    }

    @Override
    public TipoTarefaOutputDto create(TipoTarefaInputDto entity) {
        entity.ValidarTipoTarefa();

        var model = modelMapper.map(entity, TipoTarefa.class);
        var tipoTarefa = repository.save(model);

        return modelMapper.map(tipoTarefa, TipoTarefaOutputDto.class);
    }

    @Override
    public TipoTarefaOutputDto getById(UUID id) {
        var model = repository.findById(id);

        if(model.isEmpty())
            throw new NotFoundErrorException("Não foi encontrado um tipo de tarefa com esse ID");

        return modelMapper.map(model.get(), TipoTarefaOutputDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        getById(id);
        repository.deleteById(id);
    }

    @Override
    public TipoTarefaOutputDto update(UUID id, TipoTarefaInputDto entity) {
        TipoTarefa tipo = modelMapper.map(entity, TipoTarefa.class);
        tipo.setId(id);

        TipoTarefa tipoTarefa = repository.save(tipo);
        return modelMapper.map(tipoTarefa, TipoTarefaOutputDto.class);
    }
}