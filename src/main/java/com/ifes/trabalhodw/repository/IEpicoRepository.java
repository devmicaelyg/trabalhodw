package com.ifes.trabalhodw.repository;

import com.ifes.trabalhodw.model.entity.Epico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IEpicoRepository extends JpaRepository<Epico, UUID> {

    @Query("select e from Epico e where e.projeto.id = ?1")
    List<Epico> findAllByProjeto(UUID uuid);
}
