package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.ProjetoApp;
import com.ifes.trabalhodw.model.dto.OutputDto.ProjetoOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ProjetoOutputDto> getAll(){
        return application.getAll();
    }

    @GetMapping
    public ProjetoOutputDto getById(@RequestParam("Id") UUID id) { return application.getById(id);}

    @PostMapping
    public ProjetoOutputDto create(@RequestBody ProjetoOutputDto projeto){ return application.create(projeto); }

    @PutMapping
    public ProjetoOutputDto update(@RequestParam("Id") UUID id, @RequestBody ProjetoOutputDto projeto){ return application.create(projeto); }

    @DeleteMapping
    public void delete(@RequestParam("Id") UUID id){ application.deleteById(id); }
}
