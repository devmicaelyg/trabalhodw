package com.ifes.trabalhodw.model.entity.tipos;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

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

    @ManyToOne
    @JoinColumn(name = "tipo_historia_usuario_id")
    private TipoHistoriaUsuario tipoHistoriaUsuario;

    @ManyToMany
    private List<TipoTarefa> dependecias;
}
