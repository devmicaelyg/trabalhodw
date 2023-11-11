package com.ifes.trabalhodw.application;

import java.util.List;

public interface IGenericApp<OutputDto, InputDto, Id> {
    OutputDto create(InputDto entity);

    OutputDto getById(Id id);

    void deleteById(Id id);

    List<OutputDto> getAll();

    OutputDto update(Id id, InputDto entity);
}
