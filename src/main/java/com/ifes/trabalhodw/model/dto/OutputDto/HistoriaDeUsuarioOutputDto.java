package com.ifes.trabalhodw.model.dto.OutputDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifes.trabalhodw.model.entity.Tarefa;
import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import com.ifes.trabalhodw.model.entity.tipos.TipoPrioridade;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class HistoriaDeUsuarioOutputDto {
    private UUID id;
    private String titulo;
    private String descricao;
    private TipoPrioridade prioridade;
    private boolean isFinalizada;
    private String dataCriacao;
    private TipoHistoriaUsuario tipoHistoriaUsuario;
    private List<TarefaOutputDto> tarefas;
    private List<HistoriaDeUsuarioOutputDto> dependencias;
    private UUID epicoId;
}
