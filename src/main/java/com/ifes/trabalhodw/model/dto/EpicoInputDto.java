package com.ifes.trabalhodw.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifes.trabalhodw.model.entity.TipoPrioridade;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class EpicoInputDto {
    private String titulo;
    private String descricao;
    private TipoPrioridade prioridade;
    @JsonIgnore
    private UUID historiaDeUsuarioId;
}
