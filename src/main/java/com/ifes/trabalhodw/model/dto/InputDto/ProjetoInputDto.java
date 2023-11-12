package com.ifes.trabalhodw.model.dto.InputDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoInputDto {
    private String nome;
    private String descricao;


    public void ValidarProjeto(){
        if(this.getNome().isEmpty())
            throw new RuntimeException("O nome do projeto é obrigatório.");
    }
}
