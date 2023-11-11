package com.ifes.trabalhodw.model.dto.InputDto;

import com.ifes.trabalhodw.model.entity.tipos.TipoPrioridade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoriaDeUsuarioInputDto {
    private String titulo;
    private String descricao;
    private TipoPrioridade prioridade;
    private UUID epicoId;
    private List<UUID> dependencias;
    private UUID tipoHistoriaUsuarioId;
}
