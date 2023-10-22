package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.HistoriaUsuarioApp;
import com.ifes.trabalhodw.application.IGenericApp;
import com.ifes.trabalhodw.model.dto.HistoriaDeUsuarioDto;
import com.ifes.trabalhodw.model.entity.HistoriaDeUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/historia")
public class HistoriaDeUsuarioController {
    private final IGenericApp<HistoriaDeUsuario, HistoriaDeUsuarioDto, UUID> app;

    @Autowired
    public HistoriaDeUsuarioController(HistoriaUsuarioApp app) {
        this.app = app;
    }
    @GetMapping("/all")
    public List<HistoriaDeUsuario> getAllHistoriaDeUsuario() {
        return app.getAll();
    }

    @GetMapping
    public Optional<HistoriaDeUsuario> getHistoriaDeUsuario(@RequestParam("id") UUID id) {
        return app.getById(id);
    }
    @PutMapping
    public HistoriaDeUsuario createHistoriaDeUsuario(@RequestBody HistoriaDeUsuarioDto dto) {
        return app.create(dto);
    }

    @DeleteMapping
    public void deleteHistoriaDeUsuario(@RequestParam("Id") UUID id) {
        app.delete(id);
    }
}
