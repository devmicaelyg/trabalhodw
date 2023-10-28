package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.TipoEpicoApp;
import com.ifes.trabalhodw.model.dto.TipoEpicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tipoepico")
public class TipoEpicoController {
    @Autowired
    private TipoEpicoApp application;

    @GetMapping("/all")
    public List<TipoEpicoDto> getAll(){
        return application.getAll();
    }

    @GetMapping
    public TipoEpicoDto getById(@RequestParam("Id") UUID id) { return application.getById(id);}

    @PostMapping
    public TipoEpicoDto create(@RequestBody TipoEpicoDto tipoEpico){ return application.create(tipoEpico); }

    @PutMapping
    public TipoEpicoDto update(@RequestParam("Id") UUID id, @RequestBody TipoEpicoDto tipoEpicoDto){
        return application.update(id, tipoEpicoDto);
    }

    @DeleteMapping
    public void delete(@RequestParam("Id") UUID id){ application.deleteById(id); }
}
