package com.ifes.trabalhodw.model.entity;

import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import com.ifes.trabalhodw.model.entity.tipos.TipoPrioridade;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriaDeUsuario {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    private String titulo;
    private String descricao;
    private TipoPrioridade prioridade;
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
    private List<HistoriaDeUsuario> dependencias = new ArrayList<>();

    @OneToMany(mappedBy = "historiaDeUsuario")
    private List<Tarefa> tarefas = new ArrayList<>();
}
