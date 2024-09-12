package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entites.BaseEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public  abstract class BaseEnittiyRepoImpl <T extends BaseEntity<ID>,ID extends Serializable> implements BaseEnitityRepo<T,ID> {
public final EntityManager entityManager;

    protected BaseEnittiyRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public T save(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public T update(T entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public boolean deleteByID(ID id) {
entityManager.getTransaction().begin();
entityManager.remove(entityManager.find(BaseEntity.class, id));
entityManager.getTransaction().commit();
return true;
    }

    @Override
    public boolean delelte(T eintity) {
        entityManager.getTransaction().begin();
        entityManager.remove(eintity);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public T findById(ID id) {
        entityManager.getTransaction().begin();
        T founded;
        try {
            founded = entityManager.find(getEntityClass(),id);
        }
        finally {
            entityManager.close();
        }
        entityManager.getTransaction().commit();
        return founded;
    }

    @Override
    public List<T> findAll() {
entityManager.getTransaction().begin();
        TypedQuery<T> query = entityManager.createQuery("SELECT e FROM " + getEntityClass().getName() + " e", getEntityClass());

        return query.getResultList();
    }
    public abstract Class<T> getEntityClass();

}
