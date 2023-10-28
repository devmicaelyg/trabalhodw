package com.ifes.trabalhodw.model.dto.tipos;

import com.ifes.trabalhodw.exception.RequiredFieldException;
import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoTarefaDto {
    private UUID id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "tipo_historia_usuario_id")
    private TipoHistoriaUsuario tipoHistoriaUsuario;

    public void ValidarTipoTarefa(){
        if(this.getDescricao().isEmpty() || this.getTipoHistoriaUsuario().getId() == null){
            throw new RequiredFieldException("A descrição e o tipo de história de usuário são campos obrigatórios.");
        }
    }
}
