package com.ifes.trabalhodw.model.entity;

import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import com.ifes.trabalhodw.model.entity.tipos.TipoPrioridade;
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
    @JoinColumn(name = "fk_tipo_historia")
    private TipoHistoriaUsuario operacaoHistoriaDeUsuario;

    @ManyToOne
    @JoinColumn(name = "epico_id")
    private Epico epico;

    public HistoriaDeUsuario() {
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }
}
