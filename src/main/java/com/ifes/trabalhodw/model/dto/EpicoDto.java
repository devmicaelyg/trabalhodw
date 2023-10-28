package com.ifes.trabalhodw.model.dto;

import com.ifes.trabalhodw.model.entity.Epico;
import com.ifes.trabalhodw.model.entity.HistoriaDeUsuario;
import com.ifes.trabalhodw.model.entity.Projeto;
import com.ifes.trabalhodw.model.entity.tipos.TipoEpico;
import com.ifes.trabalhodw.model.entity.tipos.TipoPrioridade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpicoDto {
    private UUID id;
    private String titulo;
    private String descricao;
    private TipoPrioridade relevancia;
    private Date dataCriacao;
    @ManyToOne
    @JoinColumn(name = "tipo_epico_id")
    private TipoEpico tipoEpico;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @ManyToMany
    @JoinTable(name = "dependencias_epico",
            joinColumns = @JoinColumn(name = "epico_id"),
            inverseJoinColumns = @JoinColumn(name = "dependencia_id"))
    private List<Epico> dependencias;

    @OneToMany(mappedBy = "epico")
    private List<HistoriaDeUsuarioDto> historiasDeUsuarios;

}
