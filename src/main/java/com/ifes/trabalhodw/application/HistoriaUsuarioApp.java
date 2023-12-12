package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.exception.DependeciasCiclicasException;
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
    private final GrafoDependecia<HistoriaDeUsuario> grafoDependecia;

    @Autowired
    public HistoriaUsuarioApp(HistoriaDeUsuarioRepository repository, ModelMapper mapper, GrafoDependecia<HistoriaDeUsuario> grafoDependecia) {
        this.repository = repository;
        this.mapper = mapper;
        this.grafoDependecia = grafoDependecia;
    }

    @Override
    public HistoriaDeUsuarioOutputDto create(HistoriaDeUsuarioInputDto entity) {
        HistoriaDeUsuario hist = this.mapper.map(entity, HistoriaDeUsuario.class);
        System.out.println("Antes de salvar "+ hist);
        this.repository.save(hist);
        System.out.println("Depois de salvar "+ hist);

        if(grafoDependecia.possuiDependencia(hist, hist.getDependencias()))
            throw new DependeciasCiclicasException();

        return this.mapper.map(hist, HistoriaDeUsuarioOutputDto.class);
    }

    public List<HistoriaDeUsuarioOutputDto> createAll(List<HistoriaDeUsuarioInputDto> entities) {
        Type targetType = new TypeToken<List<HistoriaDeUsuarioOutputDto>>() {}.getType();
        Type targetInputType = new TypeToken<List<HistoriaDeUsuario>>() {}.getType();
        List<HistoriaDeUsuario> historias = this.mapper.map(entities, targetInputType);
        historias = this.repository.saveAll(historias);
        return this.mapper.map(historias, targetType);
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

       if(grafoDependecia.possuiDependencia(histNova, histNova.getDependencias()))
            throw new DependeciasCiclicasException();

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