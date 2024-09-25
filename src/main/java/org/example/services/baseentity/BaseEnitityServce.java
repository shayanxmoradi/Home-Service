package org.example.services.baseentity;

import org.example.entites.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseEnitityServce <T extends BaseEntity<ID>, ID extends Serializable> {

    T save(T entity);
    T update(T entity);
    boolean delete(T entity);
    boolean deleteById(ID id);
    T findById(ID id);
    List<T> findAll();
    public Optional<List<T>> findByAttribute(Class<T> clazz, String attributeName, Object attributeValue);


}
