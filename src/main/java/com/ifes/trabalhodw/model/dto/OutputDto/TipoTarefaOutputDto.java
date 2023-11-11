package com.ifes.trabalhodw.model.dto.OutputDto;

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
public class TipoTarefaOutputDto {
    private UUID id;

    private String descricao;
    private TipoHistoriaUsuario tipoHistoriaUsuarioId;

}