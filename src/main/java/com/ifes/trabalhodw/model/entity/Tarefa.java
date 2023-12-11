package com.ifes.trabalhodw.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Tarefa {
     @Id
     @GeneratedValue(generator = "uuid2")
     @GenericGenerator(name = "uuid2", strategy = "uuid2")
     private UUID id;
     private String titulo;
     private String descricao;
     private StatusTarefa statusTarefa;

     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "historia_de_usuario_id")
     private HistoriaDeUsuario historiaDeUsuario;

     @ManyToMany
     @JoinTable(name = "dependencias_tarefa",
             joinColumns = @JoinColumn(name = "tarefa_id"),
             inverseJoinColumns = @JoinColumn(name = "dependencia_id"))
     private List<Tarefa> dependencias;

}
