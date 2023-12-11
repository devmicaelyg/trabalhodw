package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.TipoHistoriaUsuarioApp;
import com.ifes.trabalhodw.model.dto.InputDto.TipoHistoriaUsuarioInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.TipoHistoriaUsuarioOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tipohistoriausuario")
public class TipoHistoriaUsuarioController {
    @Autowired
    private TipoHistoriaUsuarioApp application;

    @GetMapping("/all")
    public List<TipoHistoriaUsuarioOutputDto> getAll(){
        return application.getAll();
    }

    @GetMapping
    public TipoHistoriaUsuarioOutputDto getById(@RequestParam("Id") UUID id) { return application.getById(id);}

    @GetMapping("/epico")
    public List<TipoHistoriaUsuarioOutputDto> getByEpico(@RequestParam("EpicoId") UUID id) { return application.getByEpico(id);}


    @PostMapping
    public TipoHistoriaUsuarioOutputDto create(@RequestBody TipoHistoriaUsuarioInputDto tipo){ return application.create(tipo); }

    @PutMapping
    public TipoHistoriaUsuarioOutputDto update(@RequestParam("Id") UUID id, @RequestBody TipoHistoriaUsuarioInputDto tipo){
        return application.update(id ,tipo);
    }

    @DeleteMapping
    public void delete(@RequestParam("Id") UUID id){ application.deleteById(id); }
}