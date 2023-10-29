package com.ifes.trabalhodw.model.dto.InputDto;

import com.ifes.trabalhodw.model.entity.tipos.TipoPrioridade;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class HistoriaDeUsuarioInputDto {
    private String titulo;
    private String descricao;
    private TipoPrioridade prioridade;
    private UUID epicoId;
    private List<UUID> dependencias;
}
