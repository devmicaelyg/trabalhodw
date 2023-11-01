package com.ifes.trabalhodw.repository;

import com.ifes.trabalhodw.model.entity.tipos.TipoEpico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITipoEpicoRepository extends JpaRepository<TipoEpico, UUID> {
}
