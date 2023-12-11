package com.ifes.trabalhodw.model.dto.OutputDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoEpicoOutputDto {
    private UUID id;
    private String descricao;
    private List<UUID> dependecias;
}