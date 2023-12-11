package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.model.dto.InputDto.HistoriaDeUsuarioInputDto;
import com.ifes.trabalhodw.model.dto.InputDto.TarefaInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.HistoriaDeUsuarioOutputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.TarefaOutputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.TipoHistoriaUsuarioOutputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.TipoTarefaOutputDto;
import com.ifes.trabalhodw.model.entity.Epico;
import com.ifes.trabalhodw.model.entity.HistoriaDeUsuario;
import com.ifes.trabalhodw.model.entity.StatusTarefa;
import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import com.ifes.trabalhodw.model.entity.tipos.TipoTarefa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutoGenerationApp {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private HistoriaUsuarioApp historiaDeUsuarioApp;


    @Autowired
    private TarefaApp tarefaApp;

    @Autowired
    private TipoTarefaApp tipoTarefaApp;

    public List<HistoriaDeUsuarioOutputDto> generateHistoriaDeUsuario(Epico epico, String historyObjective, List<TipoHistoriaUsuarioOutputDto> historiaUsuarios){
        List<HistoriaDeUsuarioOutputDto> historias = new ArrayList<>();
        var tiposTarefa = tipoTarefaApp.getAll();

        for(TipoHistoriaUsuarioOutputDto tipo: historiaUsuarios){
            List<TipoTarefaOutputDto> tarefas = tiposTarefa
                    .stream().filter(t -> t.getTipoHistoriaUsuario().getId() == tipo.getId())
                    .toList();

            HistoriaDeUsuarioInputDto hist = new HistoriaDeUsuarioInputDto(
                    tipo.getDescricao() + " Automatico",
                    tipo.getDescricao() + " " + historyObjective,
                    epico.getRelevancia(),
                    epico.getId(),
                    new ArrayList<>(),
                    tipo.getId()
            );

            HistoriaDeUsuarioOutputDto outputDto = historiaDeUsuarioApp.create(hist);
            this.generateTarefa(outputDto, historyObjective, tarefas);
            historias.add(mapper.map(hist, HistoriaDeUsuarioOutputDto.class));
        }
        return historias;
    }

    public List<TarefaOutputDto> generateTarefa(HistoriaDeUsuarioOutputDto hist, String descricaoObjetivo, List<TipoTarefaOutputDto> tipoTarefas) {
        List<TarefaOutputDto> tarefas = new ArrayList<>();
        for (TipoTarefaOutputDto tipoTarefa : tipoTarefas) {
            TarefaInputDto tarefa = new TarefaInputDto();
            tarefa.setDescricao(descricaoObjetivo);
            tarefa.setTitulo(tipoTarefa.getDescricao());
            tarefa.setHistoriaDeUsuarioId(hist.getId());
            tarefa.setStatusTarefa(StatusTarefa.AGUARDANDO);
            tarefaApp.create(tarefa);
            tarefas.add(mapper.map(tarefa, TarefaOutputDto.class));
        }

        return tarefas;
    }
}
