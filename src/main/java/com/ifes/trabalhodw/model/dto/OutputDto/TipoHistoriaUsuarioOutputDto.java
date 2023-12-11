package com.ifes.trabalhodw.model.dto.OutputDto;

import com.ifes.trabalhodw.model.entity.tipos.TipoEpico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoHistoriaUsuarioOutputDto {
    private UUID id;
    private String descricao;
    private TipoEpicoOutputDto tipoEpico;
    private List<TarefaOutputDto> tarefas;

}