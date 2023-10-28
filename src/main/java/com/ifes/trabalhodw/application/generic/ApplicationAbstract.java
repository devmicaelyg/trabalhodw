package com.ifes.trabalhodw.application.generic;

import com.ifes.trabalhodw.application.generic.IGenericApplication;
import com.ifes.trabalhodw.repository.generic.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ApplicationAbstract <T, W extends IGenericRepository<T>> implements IGenericApplication<T> {

    @Autowired
    W repository;

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T create(T entity){
        return repository.save(entity);
    }

    @Override
    public T getById(UUID id) {
        return repository.getById(id);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
