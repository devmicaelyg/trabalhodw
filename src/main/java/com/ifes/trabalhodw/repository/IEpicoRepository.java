package com.ifes.trabalhodw.repository;

import com.ifes.trabalhodw.model.entity.Epico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEpicoRepository extends JpaRepository<Epico, UUID> {
}
