package com.ifes.trabalhodw.repository;

import com.ifes.trabalhodw.model.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ITarefaRepository extends JpaRepository<Tarefa, UUID> {

    @Query("from Tarefa t where t.historiaDeUsuario.epico.projeto.id = :id")
    public List<Tarefa> findAllByProjetoId(UUID id);
}
