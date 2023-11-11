package com.ifes.trabalhodw.repository;

import com.ifes.trabalhodw.model.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ITarefaRepository extends JpaRepository<Tarefa, UUID> {

}
