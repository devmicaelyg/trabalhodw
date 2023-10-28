package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.exception.NotFoundErrorException;
import com.ifes.trabalhodw.model.dto.EpicoInputDto;
import com.ifes.trabalhodw.model.dto.HistoriaDeUsuarioDto;
import com.ifes.trabalhodw.model.entity.Epico;
import com.ifes.trabalhodw.model.entity.HistoriaDeUsuario;
import com.ifes.trabalhodw.repository.HistoriaDeUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HistoriaUsuarioApp implements IGenericApp<HistoriaDeUsuario, HistoriaDeUsuarioDto, UUID> {

    private final JpaRepository<HistoriaDeUsuario, UUID> repository;
    private final IGenericApp<Epico, EpicoInputDto, UUID> epicoApp;

    @Autowired
    public HistoriaUsuarioApp(HistoriaDeUsuarioRepository repository, EpicoApp epicoApp) {
        this.repository = repository;
        this.epicoApp = epicoApp;
    }

    @Override
    public HistoriaDeUsuario create(HistoriaDeUsuarioDto entity) {
        HistoriaDeUsuario hist = new HistoriaDeUsuario();
        hist.setTitulo(entity.getTitulo());
        hist.setDescricao(entity.getDescricao());
        hist.setRelevancia(entity.getPrioridade());

        if (entity.getEpicoId() != null) {
            Epico epico = epicoApp.getById(entity.getEpicoId()).orElseGet(() -> null);
            hist.setEpico(epico);
        }
        return repository.save(hist);
    }

    @Override
    public Optional<HistoriaDeUsuario> getById(UUID id) {
        return this.repository.findById(id);
    }

    @Override
    public void delete(UUID id) {
        this.repository.deleteById(id);
    }

    @Override
    public HistoriaDeUsuario update(UUID id, HistoriaDeUsuarioDto entity) {
        HistoriaDeUsuario hist = this.repository.findById(id).orElseThrow(() -> new NotFoundErrorException("Historia de Usuario não encontrada"));
        if (entity.getTitulo() != null) {
            hist.setTitulo(entity.getTitulo());
        }
        if (entity.getDescricao() != null) {
            hist.setDescricao(entity.getDescricao());
        }
        if (entity.getPrioridade() != null) {
            hist.setRelevancia(entity.getPrioridade());
        }
        if (entity.getEpicoId() != null) {
            Epico epico = epicoApp.getById(entity.getEpicoId()).orElseGet(() -> null);
            hist.setEpico(epico);
        }
        return this.repository.save(hist);
    }

    @Override
    public List<HistoriaDeUsuario> getAll() {
        return this.repository.findAll();
    }


}
