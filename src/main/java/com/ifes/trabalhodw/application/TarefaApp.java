package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.application.generic.IGenericApplication;
import com.ifes.trabalhodw.model.dto.EpicoDto;
import com.ifes.trabalhodw.model.dto.ProjetoDto;
import com.ifes.trabalhodw.model.dto.TarefaDto;
import com.ifes.trabalhodw.model.dto.tipos.TipoEpicoDto;
import com.ifes.trabalhodw.model.entity.StatusTarefa;
import com.ifes.trabalhodw.model.entity.Tarefa;
import com.ifes.trabalhodw.repository.ITarefaRepository;
import com.ifes.trabalhodw.repository.ITipoEpicoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TarefaApp {
    @Autowired
    private ITarefaRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TarefaDto> getByHistoriaUsuario(UUID historiaUsuarioId){
         List<Tarefa> tarefaList = new ArrayList<>();

         var tarefas = repository.findAll();

        for (Tarefa tarefa : tarefas) {
            if(tarefa.getHistoriaDeUsuario().getId().equals(historiaUsuarioId))
                tarefaList.add(tarefa);
        }
        Type targetType = new TypeToken<List<TarefaDto>>() {}.getType();
        List<TarefaDto> tarefaDtoList = modelMapper.map(tarefaList, targetType);

        return tarefaDtoList;
    }

    public TarefaDto create(Tarefa tarefa){
        var tarefaNova = repository.save(tarefa);
        return modelMapper.map(tarefaNova, TarefaDto.class);
    }
}
