package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.IGenericApp;
import com.ifes.trabalhodw.application.TarefaApp;
import com.ifes.trabalhodw.model.dto.InputDto.TarefaInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.TarefaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    private final IGenericApp<TarefaOutputDto, TarefaInputDto,UUID> tarefaApp;

    @Autowired
    public TarefaController(TarefaApp app) {
        this.tarefaApp = app;
    }

    @DeleteMapping
    public void delete(@RequestParam("Id") UUID uuid) {
        tarefaApp.deleteById(uuid);
    }

    @PutMapping
    public TarefaOutputDto update(@RequestParam("Id") UUID uuid, @RequestBody TarefaInputDto tarefaInputDto) {
        return tarefaApp.update(uuid, tarefaInputDto);
    }

    @GetMapping
    public TarefaOutputDto getById(@RequestParam("Id") UUID uuid) {
        return tarefaApp.getById(uuid);
    }
}
