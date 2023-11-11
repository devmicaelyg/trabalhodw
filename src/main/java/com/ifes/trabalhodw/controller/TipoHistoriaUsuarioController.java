package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.TipoHistoriaUsuarioApp;
import com.ifes.trabalhodw.model.dto.tipos.TipoHistoriaUsuarioDto;
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
    public List<TipoHistoriaUsuarioDto> getAll(){
        return application.getAll();
    }

    @GetMapping
    public TipoHistoriaUsuarioDto getById(@RequestParam("Id") UUID id) { return application.getById(id);}

    @PostMapping
    public TipoHistoriaUsuarioDto create(@RequestBody TipoHistoriaUsuarioDto tipo){ return application.create(tipo); }

    @PutMapping
    public TipoHistoriaUsuarioDto update(@RequestBody TipoHistoriaUsuarioDto tipo){
        return application.update(tipo);
    }

    @DeleteMapping
    public void delete(@RequestParam("Id") UUID id){ application.deleteById(id); }
}