package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.EpicoApp;
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

    private final EpicoApp epicoApp;

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
    public EpicoOutputDto getById(@RequestParam("Id") UUID id) {
        return epicoApp.getById(id);
    }

    @PutMapping
    public EpicoOutputDto update(@RequestParam("Id") UUID id, EpicoInputDto epicoInputDto) {
        return epicoApp.update(id, epicoInputDto);
    }
    @DeleteMapping
    public void delete(@RequestParam("Id") UUID id) {
        epicoApp.deleteById(id);
    }
}