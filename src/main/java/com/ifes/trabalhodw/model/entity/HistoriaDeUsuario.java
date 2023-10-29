package com.ifes.trabalhodw.model.entity;

import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import com.ifes.trabalhodw.model.entity.tipos.TipoPrioridade;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
    private TipoPrioridade relevancia;
    private boolean isFinalizada = false;
    @NotNull
    private Date dataCriacao = new Date();

    @ManyToOne
    @JoinColumn(name = "tipo_historia_id")
    private TipoHistoriaUsuario tipoHistoriaUsuario;

    @ManyToOne
    @JoinColumn(name = "epico_id")
    private Epico epico;

    @ManyToMany
    @JoinTable(name = "dependencias_historia_usuario",
            joinColumns = @JoinColumn(name = "historia_usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "dependencia_id"))
    private List<HistoriaDeUsuario> dependencias;

    @OneToMany
    private List<Tarefa> tarefas;

}
