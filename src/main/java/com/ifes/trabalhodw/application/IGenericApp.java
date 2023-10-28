package com.ifes.trabalhodw.application;

import java.util.List;
import java.util.Optional;

public interface IGenericApp<T, InputDto, Id> {
    T create(InputDto entity);

    Optional<T> getById(Id id);

    void delete(Id id);

    List<T> getAll();

    T update(Id id, InputDto entity);
}
