package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.model.dto.InputDto.HistoriaDeUsuarioInputDto;
import com.ifes.trabalhodw.model.dto.InputDto.TarefaInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.HistoriaDeUsuarioOutputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.TarefaOutputDto;
import com.ifes.trabalhodw.model.entity.Epico;
import com.ifes.trabalhodw.model.entity.HistoriaDeUsuario;
import com.ifes.trabalhodw.model.entity.StatusTarefa;
import com.ifes.trabalhodw.model.entity.Tarefa;
import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import com.ifes.trabalhodw.model.entity.tipos.TipoTarefa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutoGenerationApp {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private HistoriaUsuarioApp historiaDeUsuarioApp;

    @Autowired
    private TarefaApp tarefaApp;

    public List<HistoriaDeUsuarioOutputDto> generateHistoriaDeUsuario(Epico epico, String historyObjective){
        List<HistoriaDeUsuarioOutputDto> historias = new ArrayList<>();
        for(TipoHistoriaUsuario tipo: epico.getTipoEpico().getTiposHistoriaUsuario()){
            HistoriaDeUsuario hist = new HistoriaDeUsuario();
            hist.setTitulo(tipo.getDescricao() + "Automatico");
            hist.setDescricao(tipo.getDescricao() + " " + historyObjective);
            hist.setEpico(epico);
            hist.setRelevancia(epico.getRelevancia());
            hist.setTipoHistoriaUsuario(tipo);

            historiaDeUsuarioApp.create(mapper.map(hist, HistoriaDeUsuarioInputDto.class));
            generateTarefa(hist, historyObjective);
            historias.add(mapper.map(hist, HistoriaDeUsuarioOutputDto.class));
        }
        return historias;
    }

    public List<TarefaOutputDto> generateTarefa(HistoriaDeUsuario hist, String descricaoObjetivo) {
        List<TarefaOutputDto> tarefas = new ArrayList<>();
        for (TipoTarefa tipoTarefa : hist.getTipoHistoriaUsuario().getTipoTarefas()) {
            Tarefa tarefa = new Tarefa();
            tarefa.setDescricao(descricaoObjetivo);
            tarefa.setTitulo(tipoTarefa.getDescricao());
            tarefa.setHistoriaDeUsuario(hist);
            tarefa.setStatusTarefa(StatusTarefa.AGUARDANDO);
            tarefaApp.create(mapper.map(tarefa, TarefaInputDto.class));
            tarefas.add(mapper.map(tarefa, TarefaOutputDto.class));
        }

        return tarefas;
    }
}
