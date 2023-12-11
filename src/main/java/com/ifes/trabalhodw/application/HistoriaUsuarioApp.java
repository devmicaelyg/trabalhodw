package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.exception.NotFoundErrorException;
import com.ifes.trabalhodw.model.dto.InputDto.HistoriaDeUsuarioInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.HistoriaDeUsuarioOutputDto;
import com.ifes.trabalhodw.model.entity.Epico;
import com.ifes.trabalhodw.model.entity.HistoriaDeUsuario;
import com.ifes.trabalhodw.repository.HistoriaDeUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@Service
public class HistoriaUsuarioApp implements IGenericApp<HistoriaDeUsuarioOutputDto, HistoriaDeUsuarioInputDto,  UUID> {

    private final JpaRepository<HistoriaDeUsuario, UUID> repository;
    private final ModelMapper mapper;

    @Autowired
    public HistoriaUsuarioApp(HistoriaDeUsuarioRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public HistoriaDeUsuarioOutputDto create(HistoriaDeUsuarioInputDto entity) {
        HistoriaDeUsuario hist = this.mapper.map(entity, HistoriaDeUsuario.class);
        Epico epico = new Epico();
        epico.setId(entity.getEpicoId());
        hist.setEpico(epico);
        return this.mapper.map(this.repository.save(hist), HistoriaDeUsuarioOutputDto.class);
    }

    @Override
    public HistoriaDeUsuarioOutputDto getById(UUID id) {
        var model = this.repository.findById(id);
        if (model.isEmpty()) {
            throw new NotFoundErrorException("Historia de Usuario não encontrada");
        }
        return this.mapper.map(model.get(), HistoriaDeUsuarioOutputDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }

    @Override
    public HistoriaDeUsuarioOutputDto update(UUID id, HistoriaDeUsuarioInputDto entity) {
       var histAntiga = this.repository.findById(id);
       if (histAntiga.isEmpty()) {
              throw new NotFoundErrorException("Historia de Usuario não encontrada");
       }
       HistoriaDeUsuario histNova = this.mapper.map(entity, HistoriaDeUsuario.class);
       histNova.setId(id);
       histNova.setEpico(histAntiga.get().getEpico());
       histNova = this.repository.save(histNova);
       HistoriaDeUsuarioOutputDto output = this.mapper.map(histNova, HistoriaDeUsuarioOutputDto.class);
       output.setEpicoId(histNova.getEpico().getId());
       return output;
    }

    @Override
    public List<HistoriaDeUsuarioOutputDto> getAll() {
        Type targetType = new TypeToken<List<HistoriaDeUsuarioOutputDto>>() {}.getType();
        List<HistoriaDeUsuario> historias = this.repository.findAll();
        List<HistoriaDeUsuarioOutputDto> outputs = this.mapper.map(historias, targetType);
        return outputs;
    }

    public List<HistoriaDeUsuarioOutputDto> getAllByEpico(UUID id) {
        Type targetType = new TypeToken<List<HistoriaDeUsuarioOutputDto>>() {}.getType();
        List<HistoriaDeUsuario> historias = this.repository.findAll().stream().filter(hist -> hist.getEpico().getId().equals(id)).toList();
        List<HistoriaDeUsuarioOutputDto> outputs = this.mapper.map(historias, targetType);
        return outputs;
    }

}
