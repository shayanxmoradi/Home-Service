package org.example.services;

import org.example.entites.BaseEntity;
import org.example.repositories.BaseEnitityRepo;

import java.io.Serializable;
import java.util.List;

public class BaseEntityServceImpl<T extends BaseEntity<ID>,ID extends Serializable,R extends BaseEnitityRepo<T,ID>> implements BaseEnitityRepo<T,ID> {
  public final R baseRepository;

    public BaseEntityServceImpl(R baseRepo) {
        this.baseRepository = baseRepo;
    }

    @Override
    public T save(T entity) {
        return baseRepository.save(entity);
    }

    @Override
    public T update(T entity) {
        return baseRepository.update(entity);
    }

    @Override
    public boolean deleteByID(ID id) {
        return baseRepository.deleteByID(id);
    }

    @Override
    public boolean delelte(T eintity) {
        return baseRepository.delelte(eintity);
    }






    @Override
    public T findById(ID id) {
        return baseRepository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }
}
