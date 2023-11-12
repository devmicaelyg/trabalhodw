package com.ifes.trabalhodw.model.dto.InputDto;

import com.ifes.trabalhodw.exception.RequiredFieldException;
import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoTarefaInputDto {
    private String descricao;
    private UUID tipoHistoriaUsuarioId;
}