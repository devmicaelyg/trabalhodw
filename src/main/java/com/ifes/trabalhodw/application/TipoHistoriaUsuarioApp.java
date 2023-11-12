package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.exception.NotFoundErrorException;
import com.ifes.trabalhodw.model.dto.InputDto.TipoHistoriaUsuarioInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.TipoHistoriaUsuarioOutputDto;

import com.ifes.trabalhodw.model.entity.Epico;
import com.ifes.trabalhodw.model.entity.tipos.TipoEpico;
import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import com.ifes.trabalhodw.repository.ITipoHistoriaUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TipoHistoriaUsuarioApp implements IGenericApp<TipoHistoriaUsuarioOutputDto, TipoHistoriaUsuarioInputDto, UUID> {

    private final JpaRepository<TipoHistoriaUsuario, UUID> repository;

    private ModelMapper modelMapper;

    @Autowired
    public TipoHistoriaUsuarioApp(ITipoHistoriaUsuarioRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TipoHistoriaUsuarioOutputDto> getAll() {
        Type targetType = new TypeToken<List<TipoHistoriaUsuarioOutputDto>>() {}.getType();
        var listaHist = repository.findAll();

        List<TipoHistoriaUsuarioOutputDto> listaFinal = modelMapper.map(listaHist, targetType);
        return listaFinal;
    }

    @Override
    public TipoHistoriaUsuarioOutputDto create(TipoHistoriaUsuarioInputDto entity) {

        var model = modelMapper.map(entity, TipoHistoriaUsuario.class);
        var novoTipo = repository.save(model);

        return modelMapper.map(novoTipo, TipoHistoriaUsuarioOutputDto.class);
    }

    @Override
    public TipoHistoriaUsuarioOutputDto getById(UUID id) {
        var model = repository.findById(id);

        if(model.isEmpty())
            throw new NotFoundErrorException("Não foi encontrado uma história de usuário com esse ID");

        return modelMapper.map(model.get(), TipoHistoriaUsuarioOutputDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        getById(id);
        repository.deleteById(id);
    }

    @Override
    public TipoHistoriaUsuarioOutputDto update(UUID id,TipoHistoriaUsuarioInputDto entity) {
        if(!repository.existsById(id))
            throw new NotFoundErrorException("Não foi encontrar esse tipo de história de usuário com esse ID");

        TipoHistoriaUsuario tipo = modelMapper.map(entity, TipoHistoriaUsuario.class);
        tipo.setId(id);

        TipoHistoriaUsuario tipoHistoriaUsuario = repository.save(tipo);
        return modelMapper.map(tipoHistoriaUsuario, TipoHistoriaUsuarioOutputDto.class);
    }

    public List<TipoHistoriaUsuario> getByEpico(TipoEpico tipoEpico){
        List<TipoHistoriaUsuario> historiasDeUmEpic = new ArrayList<>();
        var tipos = repository.findAll();
        return historiasDeUmEpic;
    }
}