package com.ifes.trabalhodw.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor()
public enum StatusTarefa {
    FEITO("FEITO"),
    AGUARDANDO("NAO INICIADO"),
    ATRIBUIDO("EM ANDAMENTO"),
    VALIDACAO("VALIDAÇÃO");

    private String descricao;
}
