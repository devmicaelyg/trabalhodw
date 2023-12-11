package com.ifes.trabalhodw.model.dto.OutputDto;

import com.ifes.trabalhodw.model.entity.StatusTarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaOutputDto {
    private UUID id;
    private String titulo;
    private String descricao;
    private StatusTarefa statusTarefa;
    private UUID historiaDeUsuarioId;
    private List<TarefaOutputDto> dependencias;

}
