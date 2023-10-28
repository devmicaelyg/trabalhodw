package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.application.generic.IGenericApplication;
import com.ifes.trabalhodw.model.dto.ProjetoDto;
import com.ifes.trabalhodw.model.entity.Projeto;
import com.ifes.trabalhodw.repository.IProjetoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@Service
public class ProjetoApp implements IGenericApplication<ProjetoDto> {
    @Autowired
    private IProjetoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

   @Override
   public List<ProjetoDto> getAll(){
        Type targetType = new TypeToken<List<ProjetoDto>>() {}.getType();
        var listaProduto = repository.findAll();

        List<ProjetoDto> listaProdutoDto = modelMapper.map(listaProduto, targetType);
        return listaProdutoDto;
   }

    @Override
    public ProjetoDto create(ProjetoDto entity) {
       entity.ValidarProjeto();

       var model = modelMapper.map(entity, Projeto.class);
       var projetoNovo = repository.save(model);
       return modelMapper.map(projetoNovo, ProjetoDto.class);
    }

    @Override
    public ProjetoDto getById(UUID id) {
        var model = repository.findById(id);

        if(model.isEmpty())
            throw new RuntimeException("Não foi encontrado um projeto com esse ID");

        return modelMapper.map(model, ProjetoDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public ProjetoDto update(UUID id, ProjetoDto entity) {
        //var exists = repository.findById(id);
        Projeto projeto = modelMapper.map(entity, Projeto.class);
        projeto.setId(id);

        Projeto projetoAtt = repository.save(projeto);
        return modelMapper.map(projetoAtt, ProjetoDto.class);
    }


}
