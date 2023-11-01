package com.ifes.trabalhodw.model.entity.tipos;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoSituacao {
    A_FAZER("A fazer"),
    EM_ANDAMENTO("Em andamento"),
    FINALIZADA("Finalizada");

    String descricao;
}
