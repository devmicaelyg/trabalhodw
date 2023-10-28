package com.ifes.trabalhodw.model.entity.tipos;

import com.ifes.trabalhodw.model.dto.TipoEpicoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    public TipoEpicoDto MapperToDto(){
        var entity = new TipoEpicoDto();
        entity.setDescricao(this.getDescricao());
        return entity;
    }
}
