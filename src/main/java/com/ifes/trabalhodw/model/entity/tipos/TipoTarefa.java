package com.ifes.trabalhodw.model.entity.tipos;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;
import com.ifes.trabalhodw.model.entity.tipos.TipoSituacao;

@Entity
@Data
public class TipoTarefa {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    
    private String descricao;

    private TipoSituacao situacao;

    @ManyToMany
    private List<TipoTarefa> dependecias;

    @ManyToOne
    private TipoHistoriaUsuario tipoHistoriaUsuario;
}
