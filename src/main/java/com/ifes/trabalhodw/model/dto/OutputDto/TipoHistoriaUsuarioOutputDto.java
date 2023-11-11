package com.ifes.trabalhodw.model.dto.OutputDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoHistoriaUsuarioOutputDto {
    private UUID id;
    private String descricao;
    private UUID tipoEpicoId;

}