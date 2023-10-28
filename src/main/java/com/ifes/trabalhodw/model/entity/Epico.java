package com.ifes.trabalhodw.model.entity;

import com.ifes.trabalhodw.model.entity.tipos.TipoEpico;
import com.ifes.trabalhodw.model.entity.tipos.TipoPrioridade;
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
    private TipoPrioridade relevancia;
    private Date dataCriacao;
    //private String categoria;
    @ManyToOne
    @JoinColumn(name = "tipo_epico_id")
    private TipoEpico tipoEpico;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @ManyToMany
    @JoinTable(name = "dependencias_epico",
            joinColumns = @JoinColumn(name = "epico_id"),
            inverseJoinColumns = @JoinColumn(name = "dependencia_id"))
    private List<Epico> dependencias;
}
