package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.exception.NotFoundErrorException;
import com.ifes.trabalhodw.model.dto.InputDto.EpicoInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.EpicoOutputDto;
import com.ifes.trabalhodw.model.entity.Epico;
import com.ifes.trabalhodw.repository.IEpicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public EpicoOutputDto create(EpicoInputDto entity) {
        var epico = modelMapper.map(entity, Epico.class);
        String obejtivo = pegarUltimaPalavra(epico.getDescricao());
        generationApp.generateHistoriaDeUsuario(epico, obejtivo);
        epico = repository.save(epico);
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
        epicoAtualizado.setId(id);
        epicoAtualizado = repository.save(epicoAtualizado);
        return modelMapper.map(epicoAtualizado, EpicoOutputDto.class);
    }


}
