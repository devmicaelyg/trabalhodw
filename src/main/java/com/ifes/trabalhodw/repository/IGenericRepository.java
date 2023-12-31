package com.ifes.trabalhodw.repository;

import com.ifes.trabalhodw.model.entity.HistoriaDeUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@NoRepositoryBean
public interface IGenericRepository<T> extends JpaRepository<T, UUID> {
}
