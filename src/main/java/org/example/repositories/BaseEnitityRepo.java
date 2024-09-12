package org.example.repositories;

import org.example.entites.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseEnitityRepo<T extends BaseEntity<ID>, ID extends Serializable> {

    T save(T entity);
    T update(T entity);
    boolean deleteByID(ID id);
    boolean delelte(T eintity);
    T findById(ID id);
    List<T> findAll();
}
