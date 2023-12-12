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

    @Query(value="Select t.id, t.descricao, t.status_tarefa, t.titulo, t.historia_de_usuario_id " +
            "from Tarefa as t \n" +
            "join historia_de_usuario hu on t.historia_de_usuario_id = hu.id \n" +
            "join epico ep on ep.id = hu.epico_id \n" +
            "where ep.projeto_id = ?1 ", nativeQuery = true)
    List<Tarefa> findAllByProjetoId(UUID id);

    @Query("select t from Tarefa t where t.historiaDeUsuario.id = ?1")
    List<Tarefa> findAllByHistoriaDeUsuarioId(UUID id);
}
