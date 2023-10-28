package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.application.generic.IGenericApplication;
import com.ifes.trabalhodw.model.dto.EpicoDto;
import com.ifes.trabalhodw.model.dto.ProjetoDto;
import com.ifes.trabalhodw.model.entity.Epico;
import com.ifes.trabalhodw.model.entity.Projeto;
import com.ifes.trabalhodw.repository.IEpicoRepository;
import com.ifes.trabalhodw.repository.IProjetoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@Service
public class EpicoApp implements IGenericApplication<EpicoDto> {

    @Autowired
    private IEpicoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EpicoDto> getAll() {
        Type targetType = new TypeToken<List<EpicoDto>>() {}.getType();
        var listaEpicos = repository.findAll();

        List<EpicoDto> lista = modelMapper.map(listaEpicos, targetType);
        return lista;
    }

    @Override
    public EpicoDto create(EpicoDto entity) {
        var model = modelMapper.map(entity, Epico.class);
        var epicoNovo = repository.save(model);
        return modelMapper.map(epicoNovo, EpicoDto.class);
    }

    @Override
    public EpicoDto getById(UUID id) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public EpicoDto update(EpicoDto entity) {
        return null;
    }
}
