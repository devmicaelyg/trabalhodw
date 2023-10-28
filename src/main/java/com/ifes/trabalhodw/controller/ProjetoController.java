package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.ProjetoApp;
import com.ifes.trabalhodw.model.dto.ProjetoDto;
import com.ifes.trabalhodw.model.entity.Projeto;
import com.ifes.trabalhodw.repository.IProjetoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static aj.org.objectweb.asm.Type.getType;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {
    @Autowired
    private ProjetoApp application;

    @GetMapping("/all")
    public List<ProjetoDto> getAll(){
        return application.getAll();
    }

    @GetMapping
    public ProjetoDto getById(@RequestParam("Id") UUID id) { return application.getById(id);}

    @PostMapping
    public ProjetoDto create(@RequestBody ProjetoDto projeto){ return application.create(projeto); }

    @PutMapping
    public ProjetoDto update(@RequestParam("Id") UUID id, @RequestBody ProjetoDto projeto){ return application.create(projeto); }

    @DeleteMapping
    public void delete(@RequestParam("Id") UUID id){ application.deleteById(id); }
}
