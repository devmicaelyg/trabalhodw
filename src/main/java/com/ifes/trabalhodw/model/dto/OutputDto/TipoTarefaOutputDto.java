package com.ifes.trabalhodw.model.dto.OutputDto;

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
    private UUID tipoHistoriaUsuarioId;

}