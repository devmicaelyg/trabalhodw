package com.ifes.trabalhodw.model.dto.InputDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoEpicoInputDto {
    private String descricao;

    public void ValidarTipoEpico(){
        if(this.getDescricao().isEmpty())
            throw new RuntimeException("A descrição do Tipo do Epico é obrigatório.");
    }
}
