package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.TipoEpicoApp;
import com.ifes.trabalhodw.model.dto.InputDto.TipoEpicoInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.TipoEpicoOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tipoepico")
@CrossOrigin(maxAge = 3600)
public class TipoEpicoController {
    @Autowired
    private TipoEpicoApp application;

    @GetMapping("/all")
    public List<TipoEpicoOutputDto> getAll(){
        return application.getAll();
    }

    @GetMapping
    public TipoEpicoOutputDto getById(@RequestParam("Id") UUID id) { return application.getById(id);}

    @PostMapping
    public TipoEpicoOutputDto create(@RequestBody TipoEpicoInputDto tipoEpico){ return application.create(tipoEpico); }

    @PutMapping
    public TipoEpicoOutputDto update(@RequestParam("Id") UUID id, @RequestBody TipoEpicoInputDto tipoEpicoOutputDto){
        return application.update(id, tipoEpicoOutputDto);
    }

    @DeleteMapping
    public void delete(@RequestParam("Id") UUID id){ application.deleteById(id); }
}
