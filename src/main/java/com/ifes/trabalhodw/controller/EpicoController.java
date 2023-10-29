package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.EpicoApp;
import com.ifes.trabalhodw.application.IGenericApp;
import com.ifes.trabalhodw.model.dto.InputDto.EpicoInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.EpicoOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/epico")
public class EpicoController {
    // implemente o CRUD usando EpicoApp

    private final IGenericApp<EpicoOutputDto, EpicoInputDto, UUID> epicoApp;

    @Autowired
    public EpicoController(EpicoApp epicoApp) {
        this.epicoApp = epicoApp;
    }

    @GetMapping("/all")
    public List<EpicoOutputDto> getAll() {
        return epicoApp.getAll();
    }

    @PostMapping
    public EpicoOutputDto create(EpicoInputDto epicoInputDto) {
        return epicoApp.create(epicoInputDto);
    }

    @GetMapping
    public EpicoOutputDto getById(UUID id) {
        return epicoApp.getById(id);
    }

    @PutMapping
    public EpicoOutputDto update(UUID id, EpicoInputDto epicoInputDto) {
        return epicoApp.update(id, epicoInputDto);
    }
    @DeleteMapping
    public void delete(UUID id) {
        epicoApp.deleteById(id);
    }
}