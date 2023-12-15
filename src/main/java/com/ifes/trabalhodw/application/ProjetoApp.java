package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.exception.NotFoundErrorException;
import com.ifes.trabalhodw.model.dto.InputDto.ProjetoInputDto;
import com.ifes.trabalhodw.model.dto.OutputDto.ProjetoOutputDto;
import com.ifes.trabalhodw.model.entity.HistoriaDeUsuario;
import com.ifes.trabalhodw.model.entity.Projeto;
import com.ifes.trabalhodw.repository.IProjetoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@Service
public class ProjetoApp implements IGenericApp<ProjetoOutputDto, ProjetoInputDto, UUID> {
    @Autowired
    private IProjetoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

   @Override
   public List<ProjetoOutputDto> getAll(){
        Type targetType = new TypeToken<List<ProjetoOutputDto>>() {}.getType();
        var listaProduto = repository.findAll();

        List<ProjetoOutputDto> listaProdutoDto = modelMapper.map(listaProduto, targetType);
        return listaProdutoDto;
   }

    @Override
    public ProjetoOutputDto create(ProjetoInputDto entity) {
       entity.ValidarProjeto();

       var model = modelMapper.map(entity, Projeto.class);
       var projetoNovo = repository.save(model);
       return modelMapper.map(projetoNovo, ProjetoOutputDto.class);
    }

    @Override
    public ProjetoOutputDto getById(UUID id) {
        var model = repository.findById(id);

        if(model.isEmpty())
            throw new NotFoundErrorException("Não foi encontrado um projeto com esse ID");

        return modelMapper.map(model.get(), ProjetoOutputDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public ProjetoOutputDto update(UUID id, ProjetoInputDto entity) {
        //var exists = repository.findById(id);
        Projeto projeto = modelMapper.map(entity, Projeto.class);
        projeto.setId(id);

        Projeto projetoAtt = repository.save(projeto);
        return modelMapper.map(projetoAtt, ProjetoOutputDto.class);
    }

    // Verifique se o projeto possui ciclo
}