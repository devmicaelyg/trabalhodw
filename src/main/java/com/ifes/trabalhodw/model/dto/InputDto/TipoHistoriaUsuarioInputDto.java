package com.ifes.trabalhodw.model.dto.InputDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoHistoriaUsuarioInputDto {
    private String descricao;
    private UUID tipoEpicoId;
}