package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.TarefaApp;
import com.ifes.trabalhodw.model.dto.TarefaDto;
import com.ifes.trabalhodw.model.dto.tipos.TipoEpicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaApp application;

    @GetMapping
    public List<TarefaDto> getByHistoriaUsuarioId(@RequestParam("historiaUsuarioId") UUID historiaUsuarioId) {
        return application.getByHistoriaUsuario(historiaUsuarioId);
    }
}
