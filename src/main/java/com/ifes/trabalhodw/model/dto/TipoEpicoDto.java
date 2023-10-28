package com.ifes.trabalhodw.model.dto;

import com.ifes.trabalhodw.model.entity.tipos.TipoEpico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoEpicoDto {
    private String descricao;

    public void ValidarTipoEpico(){
        if(this.getDescricao().isEmpty())
            throw new RuntimeException("A descrição do Tipo do Epico é obrigatório.");
    }
}
