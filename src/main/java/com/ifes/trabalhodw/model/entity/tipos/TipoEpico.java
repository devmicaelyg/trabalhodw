package com.ifes.trabalhodw.model.entity.tipos;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class TipoEpico {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String descricao;

    @ManyToMany
    @JoinTable(name = "dependencias_tipo_epico",
            joinColumns = @JoinColumn(name = "tipo_epico_id"),
            inverseJoinColumns = @JoinColumn(name = "dependencia_id"))
    private List<TipoEpico> dependecias;

}
