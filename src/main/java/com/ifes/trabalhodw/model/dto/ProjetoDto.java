package com.ifes.trabalhodw.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifes.trabalhodw.model.entity.Projeto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoDto {
    private String nome;
    @JsonIgnore
    private String descricao;


    public void ValidarProjeto(){
        if(this.getNome().isEmpty())
            throw new RuntimeException("O nome do projeto é obrigatório.");
    }
}
