package com.ifes.trabalhodw.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Epico{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    private String titulo;
    private String descricao;
    private TipoPrioridade prioridade;
    private Date dataCriacao;
    private Date dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "fk_tipo_epico")
    private TipoEpico tipoEpico;

    @OneToMany
    private List<HistoriaDeUsuario> historiaDeUsuario;
    @OneToMany
    private List<Epico> epicos;
}
