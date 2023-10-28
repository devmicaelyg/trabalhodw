package com.ifes.trabalhodw.repository;

import com.ifes.trabalhodw.model.entity.tipos.TipoTarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ITipoTarefa extends JpaRepository<TipoTarefa, UUID> {
}
