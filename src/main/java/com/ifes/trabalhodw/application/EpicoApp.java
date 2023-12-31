package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.utils.LibGrafos.Grafo;
import com.ifes.trabalhodw.exception.DependeciasCiclicasException;
import com.ifes.trabalhodw.exception.NotFoundErrorException;
import com.ifes.trabalhodw.model.dto.InputDto.EpicoInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.EpicoOutputDto;
import com.ifes.trabalhodw.model.entity.Epico;
import com.ifes.trabalhodw.repository.IEpicoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ifes.trabalhodw.utils.PegarUltimaPalavra.pegarUltimaPalavra;

@Service
public class EpicoApp implements IGenericApp<EpicoOutputDto, EpicoInputDto, UUID>{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IEpicoRepository repository;

    @Autowired
    private AutoGenerationApp generationApp;

    @Autowired
    private TipoHistoriaUsuarioApp tipoHistoriaUsuarioApp;

    @Autowired
    private GrafoDependecia<Epico> grafoDependecia;

    @Override
    public EpicoOutputDto create(EpicoInputDto entity) {
        var epico = modelMapper.map(entity, Epico.class);
        epico = repository.save(epico);
        String obejtivo = pegarUltimaPalavra(epico.getDescricao());
        var tiposHistoria = tipoHistoriaUsuarioApp.getByEpico(epico.getTipoEpico().getId());

        generationApp.generateHistoriaDeUsuarioAndTarefa(epico, obejtivo, tiposHistoria);

        if(epico.getDependencias() == null)
            epico.setDependencias(new ArrayList<>());

        if(grafoDependecia.possuiDependencia(epico, epico.getDependencias()))
            throw new DependeciasCiclicasException();

        return modelMapper.map(epico, EpicoOutputDto.class);

    }

    @Override
    public EpicoOutputDto getById(UUID id) {
        var epico = repository.findById(id);
        if(epico.isEmpty())
            throw new NotFoundErrorException("Não foi encontrado um epico com ID: " + id + "!");
        return modelMapper.map(epico.get(), EpicoOutputDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        var epico = repository.findById(id);
        if(epico.isEmpty())
            throw new NotFoundErrorException("Não foi encontrado um epico com ID: " + id + "!");
        repository.deleteById(id);

    }

    @Override
    public List<EpicoOutputDto> getAll() {
        List<EpicoOutputDto> outputDtos = new ArrayList<>();
        List<Epico> epicos = repository.findAll();
        for (Epico epico : epicos) {
            outputDtos.add(modelMapper.map(epico, EpicoOutputDto.class));
        }
        return outputDtos;
    }

    @Override
    public EpicoOutputDto update(UUID id, EpicoInputDto entity) {
        var epico = repository.findById(id);
        if(epico.isEmpty())
            throw new NotFoundErrorException("Não foi encontrado um epico com ID: " + id + "!");
        Epico epicoAtualizado = modelMapper.map(entity, Epico.class);
        ArrayList<Epico> dependencias = new ArrayList<>();
        for (UUID idDependencia : entity.getDependencias()) {
            Epico dependencia = new Epico();
            dependencia.setId(idDependencia);
            dependencias.add(dependencia);
        }
        epicoAtualizado.setDependencias(dependencias);
        epicoAtualizado.setId(id);
        epicoAtualizado = repository.save(epicoAtualizado);
        return modelMapper.map(epicoAtualizado, EpicoOutputDto.class);
    }

    public List<EpicoOutputDto> getByProjeto(UUID idProjeto) {
        List<Epico> epicos = repository.findAll().stream().filter(epico -> epico.getProjeto().getId().equals(idProjeto)).toList();
        Type targetType = new TypeToken<List<EpicoOutputDto>>() {}.getType();
        return modelMapper.map(epicos, targetType);
    }


    public boolean possuiCicloDependencia(UUID idEpico) {
        List<Epico> epicos = repository.findAllByProjeto(idEpico);
        Grafo<Epico> grafo = new Grafo<>();
        for (Epico epico : epicos) {
            for (Epico dependencia : epico.getDependencias()) {
                grafo.adicionarAresta(epico, dependencia, 1);
            }
        }
        return grafo.temCiclo();
    }
}