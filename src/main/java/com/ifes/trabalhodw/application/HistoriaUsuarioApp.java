package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.utils.LibGrafos.Grafo;
import com.ifes.trabalhodw.exception.NotFoundErrorException;
import com.ifes.trabalhodw.model.dto.InputDto.HistoriaDeUsuarioInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.HistoriaDeUsuarioOutputDto;
import com.ifes.trabalhodw.model.entity.HistoriaDeUsuario;
import com.ifes.trabalhodw.repository.HistoriaDeUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HistoriaUsuarioApp implements IGenericApp<HistoriaDeUsuarioOutputDto, HistoriaDeUsuarioInputDto,  UUID> {

    private final HistoriaDeUsuarioRepository repository;
    private final ModelMapper mapper;
    private final GrafoDependecia<HistoriaDeUsuario> grafoDependecia;

    @Autowired
    public HistoriaUsuarioApp(HistoriaDeUsuarioRepository repository, ModelMapper mapper, GrafoDependecia<HistoriaDeUsuario> grafoDependecia) {
        this.repository = repository;
        this.mapper = mapper;
        this.grafoDependecia = grafoDependecia;
    }

    public boolean possuiCiclo(UUID projetoUUID){
        List<HistoriaDeUsuario> historias = this.repository.findAllByProjeto(projetoUUID);

        Grafo<HistoriaDeUsuario> grafo = new Grafo<>();
        for (HistoriaDeUsuario hist : historias) {
            for (HistoriaDeUsuario dependencia : hist.getDependencias()) {
                grafo.adicionarVertice(dependencia);
                grafo.adicionarAresta(hist, dependencia, 1);
            }
        }

        return grafo.temCiclo();
    }

    @Override
    public HistoriaDeUsuarioOutputDto create(HistoriaDeUsuarioInputDto entity) {
        HistoriaDeUsuario hist = this.mapper.map(entity, HistoriaDeUsuario.class);
        this.repository.save(hist);
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
       ArrayList<HistoriaDeUsuario> dependencias = new ArrayList<>();
         for (UUID dependenciaId : entity.getDependencias()) {
              HistoriaDeUsuario dependencia = new HistoriaDeUsuario();
              dependencia.setId(dependenciaId);
              dependencias.add(dependencia);
         }

       histNova.setId(id);
       histNova.setDependencias(dependencias);

       histNova = this.repository.save(histNova);
       return this.mapper.map(histNova, HistoriaDeUsuarioOutputDto.class);
    }

    @Override
    public List<HistoriaDeUsuarioOutputDto> getAll() {
        Type targetType = new TypeToken<List<HistoriaDeUsuarioOutputDto>>() {}.getType();
        List<HistoriaDeUsuario> historias = this.repository.findAll();
        return this.mapper.map(historias, targetType);
    }

}