package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.model.dto.InputDto.EpicoInputDto;
import com.ifes.trabalhodw.model.entity.Epico;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EpicoApp implements IGenericApp<Epico, EpicoInputDto, UUID>{
    @Override
    public Epico create(EpicoInputDto entity) {
        return null;
    }

    @Override
    public Optional<Epico> getById(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public List<Epico> getAll() {
        return null;
    }

    @Override
        public Epico update(UUID id, EpicoInputDto entity) {
            return null;
        }
}
