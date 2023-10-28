package com.ifes.trabalhodw.application.generic;

import java.util.List;
import java.util.UUID;

public interface IGenericApplication<T> {
    List<T> getAll();
    T create(T entity);
    T getById(UUID id);
    void deleteById(UUID id);
}
