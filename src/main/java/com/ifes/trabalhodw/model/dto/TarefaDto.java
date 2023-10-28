package com.ifes.trabalhodw.model.dto;

import com.ifes.trabalhodw.model.entity.HistoriaDeUsuario;
import com.ifes.trabalhodw.model.entity.StatusTarefa;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDto {
    private UUID id;
    private String titulo;
    private String descricao;
    private StatusTarefa statusTarefa;

    @ManyToOne
    @JoinColumn(name = "historia_de_usuario_id")
    private HistoriaDeUsuario historiaDeUsuario;
}
