package com.ifes.trabalhodw.model.dto.OutputDto;

import com.ifes.trabalhodw.model.dto.InputDto.HistoriaDeUsuarioInputDto;
import com.ifes.trabalhodw.model.dto.InputDto.TipoEpicoInputDto;
import com.ifes.trabalhodw.model.entity.tipos.TipoPrioridade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpicoOutputDto {
    private UUID id;
    private String titulo;
    private String descricao;
    private TipoPrioridade relevancia;
    private String categoria;
    private List<HistoriaDeUsuarioOutputDto> historiasDeUsuario;
    private TipoEpicoOutputDto tipoEpico;
    private List<EpicoOutputDto> dependencias;
    private ProjetoOutputDto projeto;
}
