package com.ifes.trabalhodw.model.entity;

import com.ifes.trabalhodw.model.entity.tipos.TipoEpico;
import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import com.ifes.trabalhodw.model.entity.tipos.TipoPrioridade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
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
    private String categoria;


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
    private List<Epico> dependencias = new ArrayList<>();

    @OneToMany(mappedBy = "epico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoriaDeUsuario> historiasDeUsuario = new ArrayList<>();

}
