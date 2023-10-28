package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.application.generic.IGenericApplication;
import com.ifes.trabalhodw.exception.NotFoundErrorException;
import com.ifes.trabalhodw.model.dto.EpicoDto;
import com.ifes.trabalhodw.model.dto.HistoriaDeUsuarioDto;
import com.ifes.trabalhodw.model.dto.ProjetoDto;
import com.ifes.trabalhodw.model.dto.TarefaDto;
import com.ifes.trabalhodw.model.dto.tipos.TipoHistoriaUsuarioDto;
import com.ifes.trabalhodw.model.entity.*;
import com.ifes.trabalhodw.model.entity.tipos.TipoEpico;
import com.ifes.trabalhodw.model.entity.tipos.TipoHistoriaUsuario;
import com.ifes.trabalhodw.model.entity.tipos.TipoTarefa;
import com.ifes.trabalhodw.repository.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EpicoApp implements IGenericApplication<EpicoDto> {

    @Autowired
    private IEpicoRepository repository;

    @Autowired
    private TipoHistoriaUsuarioApp tipoHistoriaUsuarioApp;

    @Autowired
    private IHistoriaUsuarioRepository historiaUsuarioRepository;

    @Autowired
    private TipoTarefaApp tipoTarefaApp;

    @Autowired
    private TarefaApp tarefaApp;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TipoEpicoApp tipoEpicoApp;

    @Override
    public List<EpicoDto> getAll() {
        Type targetType = new TypeToken<List<EpicoDto>>() {}.getType();
        var listaEpicos = repository.findAll();

        List<EpicoDto> lista = modelMapper.map(listaEpicos, targetType);
        return lista;
    }

    @Override
    public EpicoDto create(EpicoDto entity) {
        var model = modelMapper.map(entity, Epico.class);
        var epicoNovo = repository.save(model);
        var tipoEpico = tipoEpicoApp.getById(epicoNovo.getTipoEpico().getId());

        epicoNovo.setTipoEpico(modelMapper.map(tipoEpico, TipoEpico.class));

        List<TipoHistoriaUsuario> tipoHistoriasEpic = tipoHistoriaUsuarioApp.getByEpico(epicoNovo);

        for (TipoHistoriaUsuario tipoHistoriaUsuario : tipoHistoriasEpic) {
            var hist = new HistoriaDeUsuario();
            hist.setTitulo(tipoHistoriaUsuario.getDescricao() + "Automatico");
            hist.setEpico(epicoNovo);
            hist.setRelevancia(epicoNovo.getRelevancia());
            hist.setDescricao(tipoHistoriaUsuario.getDescricao() + " " + pegarUltimaPalavra(epicoNovo.getDescricao()));
            hist.setTipoHistoriaUsuario(tipoHistoriaUsuario);
            historiaUsuarioRepository.save(hist);

            List<TipoTarefa> tipoTarefas = tipoTarefaApp.geyByTipoHistoriaUsuario(tipoHistoriaUsuario);

            for (TipoTarefa tipoTarefa : tipoTarefas) {
                var tarefa = new Tarefa();
                tarefa.setDescricao(tipoHistoriaUsuario.getDescricao() + " " +tipoTarefa.getDescricao());
                tarefa.setTitulo(tipoTarefa.getDescricao());
                tarefa.setHistoriaDeUsuario(hist);
                tarefa.setStatusTarefa(StatusTarefa.AGUARDANDO);
                tarefaApp.create(tarefa);
            }
        }

        return modelMapper.map(epicoNovo, EpicoDto.class);
    }

    public String pegarUltimaPalavra(String texto) {
        String[] palavras = texto.split(" ");
        if (palavras.length > 0) {
            return palavras[palavras.length - 1];
        } else {
            return "";
        }
    }

    @Override
    public EpicoDto getById(UUID id) {
        var model = repository.findById(id);

        if(model.isEmpty())
            throw new NotFoundErrorException("Não foi encontrado um epico com esse ID");

        return modelMapper.map(model.get(), EpicoDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        getById(id);
        deleteById(id);
    }

    @Override
    public EpicoDto update(EpicoDto entity) {
        Epico epico = modelMapper.map(entity, Epico.class);
        epico.setId(entity.getId());

        Epico epicoAtt = repository.save(epico);
        return modelMapper.map(epicoAtt, EpicoDto.class);
    }
}
