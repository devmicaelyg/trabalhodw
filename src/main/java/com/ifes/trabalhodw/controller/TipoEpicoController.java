package com.ifes.trabalhodw.controller;

import com.ifes.trabalhodw.application.generic.ApplicationAbstract;
import com.ifes.trabalhodw.model.dto.TipoEpicoDto;
import com.ifes.trabalhodw.model.entity.tipos.TipoEpico;
import com.ifes.trabalhodw.repository.TipoEpicoRepository;
import com.ifes.trabalhodw.repository.generic.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoepico")
public class TipoEpicoController {

    @Autowired
    private ApplicationAbstract<TipoEpico, TipoEpicoRepository> application;

   @GetMapping("/")
   public List<TipoEpico> getAll(){
       return application.getAll();
   }

    @PostMapping("/")
    public TipoEpicoDto create(@RequestBody TipoEpicoDto model)  {
        var retorno = application.create(model.MapperToEntity());
        return retorno.MapperToDto();
    }
}
