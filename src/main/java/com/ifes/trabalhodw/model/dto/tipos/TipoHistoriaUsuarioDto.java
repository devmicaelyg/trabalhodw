package com.ifes.trabalhodw.model.dto.tipos;

import com.ifes.trabalhodw.exception.RequiredFieldException;
import com.ifes.trabalhodw.model.entity.tipos.TipoEpico;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoHistoriaUsuarioDto {
    private UUID id;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "tipo_epico_id")
    private TipoEpico tipoEpico;

    public void ValidarTipoHistoriaUsuario(){
        if(this.descricao.isEmpty() || this.getTipoEpico().getId() == null)
            throw new RequiredFieldException("Descrição e Tipo de Epico são obrigatórios!");
    }
}
