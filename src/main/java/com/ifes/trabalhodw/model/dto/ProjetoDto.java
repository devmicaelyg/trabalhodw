package com.ifes.trabalhodw.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoDto {
    private String nome;
    private String descricao;


    public void ValidarProjeto(){
        if(this.getNome().isEmpty())
            throw new RuntimeException("O nome do projeto é obrigatório.");
    }
}
