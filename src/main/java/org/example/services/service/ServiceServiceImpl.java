package org.example.services.service;

import org.example.entites.Service;
import org.example.repositories.service.ServiceRepo;
import org.example.services.baseentity.BaseEntityServceImpl;

import java.util.List;
import java.util.Optional;

public class ServiceServiceImpl extends BaseEntityServceImpl<Service,Long,ServiceRepo> implements ServiceService {
    ServiceRepo serviceRepo;


    public ServiceServiceImpl(ServiceRepo baseRepo) {
        super(baseRepo);
        this.serviceRepo = baseRepo;
    }


     @Override
     public Optional<Service> findByName(String name) {
         return serviceRepo.findByName(name);
     }


     @Override
     public boolean addSubService(Long parentId, Service subService) {
         Optional<Service> byName = serviceRepo.findByName(subService.getName());
         if (byName.isPresent()) {
             System.err.printf("service with name %s already exists\n", subService.getName());
             return false;
         }

         return serviceRepo.addSubService(parentId, subService);
     }

     @Override
     public boolean removeSubService(Service service) {
         return false;
     }

    @Override
    public List<Service> findAllByParentId(Long parentId) {
        return serviceRepo.findAllByParentId(parentId);
    }
}
