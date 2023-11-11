package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.exception.NotFoundErrorException;
import com.ifes.trabalhodw.model.entity.Epico;
import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import com.ifes.trabalhodw.repository.ITipoHistoriaUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TipoHistoriaUsuarioApp implements IGenericApp<TipoTar> {

    @Autowired
    private ITipoHistoriaUsuarioRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TipoHistoriaUsuarioDto> getAll() {
        Type targetType = new TypeToken<List<TipoHistoriaUsuarioDto>>() {}.getType();
        var listaHist = repository.findAll();

        List<TipoHistoriaUsuarioDto> listaFinal = modelMapper.map(listaHist, targetType);
        return listaFinal;
    }

    @Override
    public TipoHistoriaUsuarioDto create(TipoHistoriaUsuarioDto entity) {
        entity.ValidarTipoHistoriaUsuario();

        var model = modelMapper.map(entity, TipoHistoriaUsuario.class);
        var novoTipo = repository.save(model);

        return modelMapper.map(novoTipo, TipoHistoriaUsuarioDto.class);
    }

    @Override
    public TipoHistoriaUsuarioDto getById(UUID id) {
        var model = repository.findById(id);

        if(model.isEmpty())
            throw new NotFoundErrorException("Não foi encontrado uma história de usuário com esse ID");

        return modelMapper.map(model.get(), TipoHistoriaUsuarioDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        getById(id);
        repository.deleteById(id);
    }

    @Override
    public TipoHistoriaUsuarioDto update(TipoHistoriaUsuarioDto entity) {
        if(!repository.existsById(entity.getId()))
            throw new NotFoundErrorException("Não foi encontrar esse tipo de história de usuário com esse ID");

        TipoHistoriaUsuario tipo = modelMapper.map(entity, TipoHistoriaUsuario.class);
        tipo.setId(entity.getId());

        TipoHistoriaUsuario tipoHistoriaUsuario = repository.save(tipo);
        return modelMapper.map(tipoHistoriaUsuario, TipoHistoriaUsuarioDto.class);
    }

    public List<TipoHistoriaUsuario> getByEpico(Epico epico){
        List<TipoHistoriaUsuario> historiasDeUmEpic = new ArrayList<>();

        if(epico.getTipoEpico().getDescricao().equals("CRUD+L")){
            var tipos = repository.findAll();

            tipos.forEach(h -> {
                if(h.getTipoEpico().getId().equals(epico.getTipoEpico().getId())){
                    historiasDeUmEpic.add(h);
                }
            });
        }

        return historiasDeUmEpic;
    }
}