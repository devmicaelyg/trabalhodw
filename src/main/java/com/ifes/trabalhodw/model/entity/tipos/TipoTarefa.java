package com.ifes.trabalhodw.model.entity.tipos;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class TipoTarefa {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    
    private String descricao;

    @ManyToMany
    private List<TipoTarefa> dependecias;

    @ManyToOne
    private TipoHistoriaUsuario tipoHistoriaUsuario;
}
