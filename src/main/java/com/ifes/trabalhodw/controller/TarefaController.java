package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.TarefaApp;
import com.ifes.trabalhodw.model.dto.InputDto.TarefaInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.TarefaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    private final TarefaApp tarefaApp;

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

    @GetMapping("/projeto")
    public List<TarefaOutputDto> getByProjeto(@RequestParam("Id") UUID uuid) {
        return tarefaApp.getByProjeto(uuid);
    }

    @GetMapping("/historiaDeUsuario")
    public List<TarefaOutputDto> getByHistoriaDeUsuario(@RequestParam("Id") UUID uuid) {
        return tarefaApp.getByHistoriaDeUsuario(uuid);
    }

    @GetMapping("/ordemExecucao")
    public List<TarefaOutputDto> getByOrdemExecucao(@RequestParam("Id") UUID uuid) {
        return tarefaApp.getOrdemExecucao(uuid);
    }

    @GetMapping("/ciclo-dependencia")
    public boolean getCicloDependencia(@RequestParam("Id") UUID uuid) {
        return tarefaApp.possuiCiclo(uuid);
    }
}
