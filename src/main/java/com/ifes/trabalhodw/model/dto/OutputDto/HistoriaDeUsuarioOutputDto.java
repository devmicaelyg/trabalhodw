package com.ifes.trabalhodw.model.dto.OutputDto;

import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import com.ifes.trabalhodw.model.entity.tipos.TipoPrioridade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoriaDeUsuarioOutputDto {
    private UUID id;
    private UUID epicoId;
    private String titulo;
    private String descricao;
    private TipoPrioridade prioridade;
    private boolean isFinalizada;
    private Date dataCriacao;
    private TipoHistoriaUsuarioOutputDto tipoHistoriaUsuario;
    private List<TipoHistoriaUsuarioOutputDto> dependencias;
}
