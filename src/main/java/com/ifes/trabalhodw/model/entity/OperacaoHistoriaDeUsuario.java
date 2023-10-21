package com.ifes.trabalhodw.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperacaoHistoriaDeUsuario {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String nome;

     @ManyToOne
     @JoinColumn(name = "historiasUsuario_id")
    private HistoriaDeUsuario historiasUsuario;
}
