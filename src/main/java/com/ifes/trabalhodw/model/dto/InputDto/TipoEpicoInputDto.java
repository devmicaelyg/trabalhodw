package com.ifes.trabalhodw.model.dto.InputDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoEpicoInputDto {
    private String descricao;
    List<UUID> dependecias;

    public void ValidarTipoEpico(){
        if(this.getDescricao().isEmpty())
            throw new RuntimeException("A descrição do Tipo do Epico é obrigatório.");
    }
}
