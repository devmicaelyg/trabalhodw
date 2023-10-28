package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.application.generic.IGenericApplication;
import com.ifes.trabalhodw.model.dto.ProjetoDto;
import com.ifes.trabalhodw.model.dto.TipoEpicoDto;
import com.ifes.trabalhodw.model.entity.Projeto;
import com.ifes.trabalhodw.model.entity.tipos.TipoEpico;
import com.ifes.trabalhodw.repository.IProjetoRepository;
import com.ifes.trabalhodw.repository.ITipoEpicoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@Service
public class TipoEpicoApp implements IGenericApplication<TipoEpicoDto> {
    @Autowired
    private ITipoEpicoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TipoEpicoDto> getAll() {
        Type targetType = new TypeToken<List<TipoEpicoDto>>() {}.getType();
        var listaTipoEpico = repository.findAll();

        List<TipoEpicoDto> listaTipoEpicoDto = modelMapper.map(listaTipoEpico, targetType);
        return listaTipoEpicoDto;
    }

    @Override
    public TipoEpicoDto create(TipoEpicoDto entity) {
        entity.ValidarTipoEpico();

        var model = modelMapper.map(entity, TipoEpico.class);
        var novoTipo = repository.save(model);
        return modelMapper.map(novoTipo, TipoEpicoDto.class);
    }

    @Override
    public TipoEpicoDto getById(UUID id) {
        var model = repository.findById(id);

        if(model.isEmpty())
            throw new RuntimeException("Não foi encontrado um tipo de epico com esse ID");

        return modelMapper.map(model.get(), TipoEpicoDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public TipoEpicoDto update(TipoEpicoDto entity) {
        var tipoEpico = repository.findById(entity.getId());

        if(tipoEpico.isPresent()){
            tipoEpico.get().setDescricao(entity.getDescricao());
            System.out.println(tipoEpico.get());
            var tt = repository.save(tipoEpico.get());
            return modelMapper.map(tt, TipoEpicoDto.class);
        }

        return null;
    }
}
