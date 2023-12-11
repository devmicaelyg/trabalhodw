package com.ifes.trabalhodw.repository;

import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ITipoHistoriaUsuarioRepository extends JpaRepository<TipoHistoriaUsuario, UUID>{
    List<TipoHistoriaUsuario> findByTipoEpicoId(UUID id);
}
