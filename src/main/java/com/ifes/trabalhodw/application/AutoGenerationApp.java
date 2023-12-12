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

    // crie método que faz uso de generateHistoriaDeUsuario e generateTarefa
    public void generateHistoriaDeUsuarioAndTarefa(Epico epico, String historyObjective, List<TipoHistoriaUsuarioOutputDto> historiaUsuarios){
        var historias = this.historiaDeUsuarioApp.createAll(generateHistoriaDeUsuario(epico, historyObjective, historiaUsuarios));
        List<TarefaInputDto> tarefas = new ArrayList<>();
        var tiposTarefa = tipoTarefaApp.getAll();

        for(HistoriaDeUsuarioOutputDto hist: historias){
            List<TipoTarefaOutputDto> tipoTarefaHist = tiposTarefa
                    .stream().filter(t -> t.getTipoHistoriaUsuario().getId() == hist.getTipoHistoriaUsuario().getId())
                    .toList();
            tarefas.addAll(generateTarefa(hist, historyObjective, tipoTarefaHist));
        }

        this.tarefaApp.createAll(tarefas);
    }


    public List<HistoriaDeUsuarioInputDto> generateHistoriaDeUsuario(Epico epico, String historyObjective, List<TipoHistoriaUsuarioOutputDto> historiaUsuarios){
        List<HistoriaDeUsuarioInputDto> historias = new ArrayList<>();

        for(TipoHistoriaUsuarioOutputDto tipo: historiaUsuarios){

            HistoriaDeUsuarioInputDto hist = new HistoriaDeUsuarioInputDto(
                    tipo.getDescricao() + " Automatico",
                    tipo.getDescricao() + " " + historyObjective,
                    epico.getRelevancia(),
                    epico.getId(),
                    new ArrayList<>(),
                    tipo.getId()
            );

            hist.setDependencias(new ArrayList<>());
            historias.add(hist);
        }
        return historias;
    }

    public List<TarefaInputDto> generateTarefa(HistoriaDeUsuarioOutputDto hist, String descricaoObjetivo, List<TipoTarefaOutputDto> tipoTarefas) {
        List<TarefaInputDto> tarefas = new ArrayList<>();
        for (TipoTarefaOutputDto tipoTarefa : tipoTarefas) {
            TarefaInputDto tarefa = new TarefaInputDto();
            tarefa.setDescricao(descricaoObjetivo);
            tarefa.setTitulo(tipoTarefa.getDescricao());
            tarefa.setHistoriaDeUsuarioId(hist.getId());
            tarefa.setStatusTarefa(StatusTarefa.AGUARDANDO);
            tarefa.setDependencias(new ArrayList<>());
            tarefas.add(tarefa);
        }
        return tarefas;
    }
}