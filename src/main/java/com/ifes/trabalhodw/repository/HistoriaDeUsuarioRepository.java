package com.ifes.trabalhodw.repository;

import com.ifes.trabalhodw.model.entity.HistoriaDeUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HistoriaDeUsuarioRepository extends JpaRepository<HistoriaDeUsuario, UUID> {

    @Query("SELECT h FROM HistoriaDeUsuario h WHERE h.epico.projeto.id = ?1")
    List<HistoriaDeUsuario> findAllByProjeto(UUID idProjeto);
}
