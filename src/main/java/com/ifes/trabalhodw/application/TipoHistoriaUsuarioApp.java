package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.exception.NotFoundErrorException;
import com.ifes.trabalhodw.model.dto.InputDto.TipoHistoriaUsuarioInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.TipoHistoriaUsuarioOutputDto;

import com.ifes.trabalhodw.model.entity.tipos.TipoEpico;
import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import com.ifes.trabalhodw.repository.ITipoHistoriaUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@Service
public class TipoHistoriaUsuarioApp implements IGenericApp<TipoHistoriaUsuarioOutputDto, TipoHistoriaUsuarioInputDto, UUID> {

    private final ITipoHistoriaUsuarioRepository repository;

    private ModelMapper modelMapper;

    private final TipoEpicoApp tipoEpicoApp;

    @Autowired
    public TipoHistoriaUsuarioApp(ITipoHistoriaUsuarioRepository repository, ModelMapper modelMapper, TipoEpicoApp tipoEpicoApp) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.tipoEpicoApp = tipoEpicoApp;
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
        var tipoEpico = tipoEpicoApp.getById(entity.getTipoEpicoId());
        model.getTipoEpico().setDescricao(tipoEpico.getDescricao());
        var novoTipo = repository.save(model);
        var output =  modelMapper.map(novoTipo, TipoHistoriaUsuarioOutputDto.class);
        return output;
    }

    @Override
    public TipoHistoriaUsuarioOutputDto getById(UUID id) {
        var model = repository.findById(id);

        if(model.isEmpty())
            throw new NotFoundErrorException("Não foi encontrado uma história de usuário com esse ID");

        var output =  modelMapper.map(model.get(), TipoHistoriaUsuarioOutputDto.class);
        return output;
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

        var output = modelMapper.map(tipoHistoriaUsuario, TipoHistoriaUsuarioOutputDto.class);
        return  output;
    }

    public List<TipoHistoriaUsuarioOutputDto> getByEpico(UUID tipoEpicoId){
        List<TipoHistoriaUsuario> historiasDeUmEpic = repository.findAll();
        historiasDeUmEpic.removeIf(historia -> !historia.getTipoEpico().getId().equals(tipoEpicoId));
        Type targetType = new TypeToken<List<TipoHistoriaUsuarioOutputDto>>() {}.getType();
        return modelMapper.map(historiasDeUmEpic, targetType);
    }
}