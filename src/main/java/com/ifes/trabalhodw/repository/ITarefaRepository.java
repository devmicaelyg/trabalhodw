package com.ifes.trabalhodw.repository;

import com.ifes.trabalhodw.model.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITarefaRepository extends JpaRepository<Tarefa, UUID> {

}
