package com.ifes.trabalhodw.model.dto.OutputDto;

import com.ifes.trabalhodw.model.entity.StatusTarefa;

import java.util.List;
import java.util.UUID;

public class TarefaOutputDto {
    private UUID id;
    private String titulo;
    private String descricao;
    private StatusTarefa statusTarefa;
    private UUID historiaDeUsuario;
    private List<TarefaOutputDto> dependencias;

}
