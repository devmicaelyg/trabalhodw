package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.TipoTarefaApp;
import com.ifes.trabalhodw.model.dto.InputDto.TipoTarefaInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.TipoTarefaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tipotarefa")
@CrossOrigin(maxAge = 3600)
public class TipoTarefaController {
    @Autowired
    private TipoTarefaApp application;

    @GetMapping("/all")
    public List<TipoTarefaOutputDto> getAll(){
        return application.getAll();
    }

    @GetMapping
    public TipoTarefaOutputDto getById(@RequestParam("Id") UUID id) { return application.getById(id);}

    @PostMapping
    public TipoTarefaOutputDto create(@RequestBody TipoTarefaInputDto tipo){ return application.create(tipo); }

    @PutMapping
    public TipoTarefaOutputDto update(@RequestParam("Id") UUID id,@RequestBody TipoTarefaInputDto tipo){
        return application.update(id, tipo);
    }

    @DeleteMapping
    public void delete(@RequestParam("Id") UUID id){ application.deleteById(id); }
}