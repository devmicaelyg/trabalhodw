package com.ifes.trabalhodw.model.entity.tipos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum TipoPrioridade {
    BAIXA("Baixa"),
    NORMAL("Normal"),
    ALTA("Alta"),
    URGENTE("Urgente");

    String descricao;
}
