package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.model.dto.InputDto.HistoriaDeUsuarioInputDto;
import com.ifes.trabalhodw.model.dto.InputDto.TarefaInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.HistoriaDeUsuarioOutputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.TarefaOutputDto;
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

@Service
public class AutoGenerationApp {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private HistoriaUsuarioApp historiaDeUsuarioApp;


    @Autowired
    private TarefaApp tarefaApp;

    public List<HistoriaDeUsuarioOutputDto> generateHistoriaDeUsuario(Epico epico, String historyObjective, List<TipoHistoriaUsuario> historiaUsuarios){
        List<HistoriaDeUsuarioOutputDto> historias = new ArrayList<>();
        for(TipoHistoriaUsuario tipo: historiaUsuarios){
            HistoriaDeUsuarioInputDto hist = new HistoriaDeUsuarioInputDto();
            hist.setTitulo(tipo.getDescricao() + " Automatico");
            hist.setDescricao(tipo.getDescricao() + " " + historyObjective);
            hist.setEpicoId(epico.getId());
            hist.setPrioridade(epico.getRelevancia());
            hist.setTipoHistoriaUsuarioId(tipo.getId());
            HistoriaDeUsuarioOutputDto outputDto = historiaDeUsuarioApp.create(hist);
            this.generateTarefa(outputDto, historyObjective, tipo.getTiposTarefa());
            historias.add(mapper.map(hist, HistoriaDeUsuarioOutputDto.class));
        }
        return historias;
    }

    public List<TarefaOutputDto> generateTarefa(HistoriaDeUsuarioOutputDto hist, String descricaoObjetivo, List<TipoTarefa> tipoTarefas) {
        List<TarefaOutputDto> tarefas = new ArrayList<>();
        for (TipoTarefa tipoTarefa : tipoTarefas) {
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
