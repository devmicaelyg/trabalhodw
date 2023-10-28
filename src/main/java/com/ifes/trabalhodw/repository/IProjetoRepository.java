package com.ifes.trabalhodw.repository;

import com.ifes.trabalhodw.application.ProjetoApp;
import com.ifes.trabalhodw.model.entity.Projeto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProjetoRepository extends JpaRepository<Projeto, UUID> {
}
