package org.example.services.baseentity;

import org.example.entites.BaseEntity;
import org.example.repositories.baseentity.BaseEnitityRepo;

import java.io.Serializable;
import java.util.List;

public class BaseEntityServceImpl<T extends BaseEntity<ID>,ID extends Serializable,R extends BaseEnitityRepo<T,ID>> implements BaseEnitityServce<T,ID> {
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
    public boolean delete(T entity) {
        return baseRepository.delelte(entity);
    }

    @Override
    public boolean deleteById(ID id) {
        return baseRepository.deleteByID(id);
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
