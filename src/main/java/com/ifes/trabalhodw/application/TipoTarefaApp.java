package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.application.generic.IGenericApplication;
import com.ifes.trabalhodw.exception.NotFoundErrorException;
import com.ifes.trabalhodw.model.dto.TipoTarefaDto;
import com.ifes.trabalhodw.model.entity.tipos.TipoTarefa;
import com.ifes.trabalhodw.repository.ITipoTarefaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@Service
public class TipoTarefaApp implements IGenericApplication<TipoTarefaDto> {

    @Autowired
    private ITipoTarefaRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TipoTarefaDto> getAll() {
        Type targetType = new TypeToken<List<TipoTarefaDto>>() {}.getType();
        var listaTipos = repository.findAll();

        List<TipoTarefaDto> listaDtos = modelMapper.map(listaTipos, targetType);
        return listaDtos;
    }

    @Override
    public TipoTarefaDto create(TipoTarefaDto entity) {
        entity.ValidarTipoTarefa();

        var model = modelMapper.map(entity, TipoTarefa.class);
        var tipoTarefa = repository.save(model);

        return modelMapper.map(tipoTarefa, TipoTarefaDto.class);
    }

    @Override
    public TipoTarefaDto getById(UUID id) {
        var model = repository.findById(id);

        if(model.isEmpty())
            throw new NotFoundErrorException("Não foi encontrado um tipo de tarefa com esse ID");

        return modelMapper.map(model.get(), TipoTarefaDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        getById(id);
        repository.deleteById(id);
    }

    @Override
    public TipoTarefaDto update(TipoTarefaDto entity) {
        if(!repository.existsById(entity.getId()))
            throw new NotFoundErrorException("Não foi possível encontrar esse tipo de tarefa com esse ID");

        TipoTarefa tipo = modelMapper.map(entity, TipoTarefa.class);
        tipo.setId(entity.getId());

        TipoTarefa tipoTarefa = repository.save(tipo);
        return modelMapper.map(tipoTarefa, TipoTarefaDto.class);
    }
}
