package com.ifes.trabalhodw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenericRepository<T> extends JpaRepository<T, Integer> {
}
