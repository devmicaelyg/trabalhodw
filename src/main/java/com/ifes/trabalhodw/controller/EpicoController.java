package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.EpicoApp;
import com.ifes.trabalhodw.model.dto.EpicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/epico")
public class EpicoController {
    @Autowired
    private EpicoApp application;

    @GetMapping("/all")
    public List<EpicoDto> getAll(){
        return application.getAll();
    }

    @GetMapping
    public EpicoDto getById(@RequestParam("Id") UUID id) { return application.getById(id);}

    @PostMapping
    public EpicoDto create(@RequestBody EpicoDto epico){ return application.create(epico); }

    @PutMapping
    public EpicoDto update(@RequestParam("Id") UUID id, @RequestBody EpicoDto epico){
        return application.create(epico); }

    @DeleteMapping
    public void delete(@RequestParam("Id") UUID id){ application.deleteById(id); }
}
