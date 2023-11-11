package com.ifes.trabalhodw.model.dto.InputDto;

import com.ifes.trabalhodw.model.entity.tipos.TipoPrioridade;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
public class EpicoInputDto {
    private String titulo;
    private String descricao;
    private String categoria;
    private TipoPrioridade relevancia;
    private UUID tipoEpicoId;
    private List<UUID> dependencias;
    private UUID projetoId;
}
