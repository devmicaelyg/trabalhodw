package com.ifes.trabalhodw.repository;

import com.ifes.trabalhodw.model.entity.tipos.TipoEpico;
import com.ifes.trabalhodw.repository.generic.IGenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TipoEpicoRepository extends IGenericRepository<TipoEpico> {
}
