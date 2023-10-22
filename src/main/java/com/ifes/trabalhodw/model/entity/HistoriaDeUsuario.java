package com.ifes.trabalhodw.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class HistoriaDeUsuario {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    private String titulo;
    private String descricao;
    private TipoPrioridade prioridade;

    @NotNull
    private Date dataCriacao;
    @NotNull
    private Date dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "operacao_id")
    private OperacaoHistoriaDeUsuario operacaoHistoriaDeUsuario;

    @ManyToOne
    @JoinColumn(name = "epico_id")
    private Epico epico;

    // contructor that initializes the dates
    public HistoriaDeUsuario() {
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }
}
