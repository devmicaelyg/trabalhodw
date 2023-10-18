package com.ifes.trabalhodw.application;

import java.util.List;

public interface IGenericApp<T> {
    T create(T entity);

    T getById(int id);

    void delete(int id);

    List<T> getAll();
}
