package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.exception.NotFoundErrorException;
import com.ifes.trabalhodw.model.dto.InputDto.TipoEpicoInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.TipoEpicoOutputDto;
import com.ifes.trabalhodw.model.entity.tipos.TipoEpico;
import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import com.ifes.trabalhodw.repository.ITipoEpicoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TipoEpicoApp implements IGenericApp<TipoEpicoOutputDto, TipoEpicoInputDto, UUID> {
    @Autowired
    private ITipoEpicoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TipoEpicoOutputDto> getAll() {
        Type targetType = new TypeToken<List<TipoEpicoOutputDto>>() {}.getType();
        var listaTipoEpico = repository.findAll();
        List<TipoEpicoOutputDto> listaTipoEpicoOutputDto = modelMapper.map(listaTipoEpico, targetType);

        for (int i=0; i<listaTipoEpico.size(); i++){
            TipoEpico tipoEpico = listaTipoEpico.get(i);
            List<UUID> listaTipoHU = new ArrayList<>();
            for(TipoHistoriaUsuario tipoHU : tipoEpico.getTiposHistoriaUsuario()){
                listaTipoHU.add(tipoHU.getId());
            }
            listaTipoEpicoOutputDto.get(i).setTiposHistoriaUsuarios(listaTipoHU);
        }

        return listaTipoEpicoOutputDto;
    }

    @Override
    public TipoEpicoOutputDto create(TipoEpicoInputDto entity) {
        entity.ValidarTipoEpico();

        var model = modelMapper.map(entity, TipoEpico.class);
        var novoTipo = repository.save(model);
        return modelMapper.map(novoTipo, TipoEpicoOutputDto.class);
    }

    @Override
    public TipoEpicoOutputDto getById(UUID id) {
        var model = repository.findById(id);

        if(model.isEmpty())
            throw new NotFoundErrorException  ("Não foi encontrado um tipo de epico com esse ID");

        TipoEpicoOutputDto tipoEpico = modelMapper.map(model.get(), TipoEpicoOutputDto.class);
        List<UUID> listaTipoHU = new ArrayList<>();
        for(TipoHistoriaUsuario tipoHU : model.get().getTiposHistoriaUsuario()){
            listaTipoHU.add(tipoHU.getId());
        }
        tipoEpico.setTiposHistoriaUsuarios(listaTipoHU);
        return tipoEpico;
    }

    @Override
    public void deleteById(UUID id) {

        repository.deleteById(id);
    }

    @Override
    public TipoEpicoOutputDto update(UUID id, TipoEpicoInputDto entity) {
        var tipoEpico = repository.findById(id);
        if(tipoEpico.isEmpty())
            throw new NotFoundErrorException("Não foi encontrado um tipo de epico com esse ID");

        TipoEpico epicoAntigo = tipoEpico.get();
        TipoEpico epicoAtualizado = modelMapper.map(entity, TipoEpico.class);
        epicoAtualizado.setId(epicoAntigo.getId());

        epicoAtualizado = repository.save(epicoAtualizado);
        return modelMapper.map(epicoAtualizado, TipoEpicoOutputDto.class);
    }

}
