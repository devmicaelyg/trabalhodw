package com.ifes.trabalhodw.repository;

// Importa a classe TipoTarefa, que parece ser uma entidade JPA representando algum tipo de tarefa
import com.ifes.trabalhodw.model.entity.tipos.TipoTarefa;

// Importa a interface JpaRepository do Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Adiciona a anotação @Repository indicando que esta interface é um bean de repositório gerenciado pelo Spring
import org.springframework.stereotype.Repository;

// Declaração da interface ITipoTarefaRepository que estende JpaRepository
public interface ITipoTarefaRepository extends JpaRepository<TipoTarefa, UUID> {
    // Essa interface herda métodos padrão do JpaRepository, como save, findById, findAll, delete, entre outros
    // Isso facilita a interação com o banco de dados em relação à entidade TipoTarefa
    // UUID representa o tipo da chave primária da entidade
}
