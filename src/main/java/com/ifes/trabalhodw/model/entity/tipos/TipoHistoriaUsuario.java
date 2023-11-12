package com.ifes.trabalhodw.model.entity.tipos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoHistoriaUsuario {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String descricao;

    @ManyToMany
    @JoinTable(name = "dependencias_tipo_historia_usuario",
            joinColumns = @JoinColumn(name = "tipo_historia_usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "dependencia_id"))
    private List<TipoHistoriaUsuario> dependencias;

    @OneToMany(mappedBy = "tipoHistoriaUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoTarefa> tiposTarefa = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private TipoEpico tipoEpico;
}
