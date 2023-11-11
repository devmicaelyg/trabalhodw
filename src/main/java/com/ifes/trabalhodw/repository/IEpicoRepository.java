package com.ifes.trabalhodw.repository;

import com.ifes.trabalhodw.model.entity.Epico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IEpicoRepository extends JpaRepository<Epico, UUID> {
}
