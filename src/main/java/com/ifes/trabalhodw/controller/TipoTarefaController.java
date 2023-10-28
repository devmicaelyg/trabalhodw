package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.TipoHistoriaUsuarioApp;
import com.ifes.trabalhodw.application.TipoTarefaApp;
import com.ifes.trabalhodw.model.dto.TipoHistoriaUsuarioDto;
import com.ifes.trabalhodw.model.dto.TipoTarefaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tipotarefa")
public class TipoTarefaController {
    @Autowired
    private TipoTarefaApp application;

    @GetMapping("/all")
    public List<TipoTarefaDto> getAll(){
        return application.getAll();
    }

    @GetMapping
    public TipoTarefaDto getById(@RequestParam("Id") UUID id) { return application.getById(id);}

    @PostMapping
    public TipoTarefaDto create(@RequestBody TipoTarefaDto tipo){ return application.create(tipo); }

    @PutMapping
    public TipoTarefaDto update(@RequestBody TipoTarefaDto tipo){
        return application.update(tipo);
    }

    @DeleteMapping
    public void delete(@RequestParam("Id") UUID id){ application.deleteById(id); }
}
