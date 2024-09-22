package org.example.repositories.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entites.Service;
import org.example.repositories.baseentity.BaseEnittiyRepoImpl;

public class ServiceRepoImpl extends BaseEnittiyRepoImpl<Service, Long> implements ServiceRepo {
    public ServiceRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Service> getEntityClass() {
        return Service.class;
    }

    @Override
    public Service findByName(String name) {
        TypedQuery<Service> query = entityManager.createQuery("SELECT s FROM Service s WHERE s.name = :servicename", Service.class);
        query.setParameter("servicename", name);
        return query.getSingleResult();
    }

    @Override
    public boolean addSubService(Service parentService, Service subService) {
//subService.setParentService(parentService);
parentService.addSubService(subService);

        entityManager.getTransaction().begin();
        entityManager.persist(subService);
        entityManager.getTransaction().commit();

        return true;
    }

    @Override
    public boolean addSubService(Long parentId, Service subService) {
        Service parent = entityManager.getReference(Service.class, parentId);
        parent.addSubService(subService);  // Link the sub-service to the parent
        entityManager.getTransaction().begin();
        entityManager.persist(subService);
        entityManager.getTransaction().commit();
    return true;}


    @Override
    public boolean removeSubService(Service service) {
        return false;
    }
}
