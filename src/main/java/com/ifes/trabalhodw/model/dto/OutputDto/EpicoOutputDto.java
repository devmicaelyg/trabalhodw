package com.ifes.trabalhodw.model.dto.OutputDto;

import com.ifes.trabalhodw.model.dto.InputDto.HistoriaDeUsuarioInputDto;
import com.ifes.trabalhodw.model.entity.tipos.TipoPrioridade;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EpicoOutputDto {
    private String titulo;
    private String descricao;
    private TipoPrioridade prioridade;
    private List<HistoriaDeUsuarioInputDto> historiasDeUsuario;
}
