package org.example.repositories.baseentity;

import org.example.entites.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseEnitityRepo<T extends BaseEntity<ID>, ID extends Serializable> {

    T save(T entity);
    T update(T entity);
    boolean deleteByID(ID id);
    boolean delelte(T eintity);
    T findById(ID id);
    public Optional<List<T>> findWithAttribute(Class<T> clazz, String attributeName, Object attributeValue);
    List<T> findAll();
}
