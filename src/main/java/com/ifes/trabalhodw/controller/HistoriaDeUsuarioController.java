package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.HistoriaUsuarioApp;
import com.ifes.trabalhodw.application.IGenericApp;
import com.ifes.trabalhodw.model.dto.InputDto.HistoriaDeUsuarioInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.HistoriaDeUsuarioOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/historia")
public class HistoriaDeUsuarioController {
    private final IGenericApp<HistoriaDeUsuarioOutputDto, HistoriaDeUsuarioInputDto, UUID> app;

    @Autowired
    public HistoriaDeUsuarioController(HistoriaUsuarioApp app) {
        this.app = app;
    }

    @GetMapping("/all")
    public List<HistoriaDeUsuarioOutputDto> getAllHistoriaDeUsuario() {
        return app.getAll();
    }

    @GetMapping
    public HistoriaDeUsuarioOutputDto getHistoriaDeUsuario(@RequestParam("id") UUID id) {
        return app.getById(id);
    }

    @PostMapping
    public HistoriaDeUsuarioOutputDto createHistoriaDeUsuario(@RequestBody HistoriaDeUsuarioInputDto dto) {
        return app.create(dto);
    }

    @DeleteMapping
    public void deleteHistoriaDeUsuario(@RequestParam("Id") UUID id) {
        app.deleteById(id);
    }

    @PutMapping
    public HistoriaDeUsuarioOutputDto updateHistoriaDeUsuario(@RequestParam("Id") UUID id, @RequestBody HistoriaDeUsuarioInputDto dto) {
        return app.update(id, dto);
    }
}
