package com.ifes.trabalhodw.model.dto;

import com.ifes.trabalhodw.model.entity.tipos.TipoEpico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoEpicoDto {
    private String nome;
    private String descricao;

    public TipoEpico MapperToEntity(){
        var entity = new TipoEpico();
        entity.setDescricao(this.getDescricao());
        return entity;
    }
}
