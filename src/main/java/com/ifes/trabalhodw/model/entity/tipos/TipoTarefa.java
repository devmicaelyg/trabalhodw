package com.ifes.trabalhodw.model.entity.tipos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
public class TipoTarefa {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String nome;
    private String descricao;

    @ManyToOne
    private TipoHistoriaUsuario tipoHistoriaUsuario;
}
