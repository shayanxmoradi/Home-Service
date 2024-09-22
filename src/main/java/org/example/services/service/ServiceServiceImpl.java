package org.example.services.service;

import org.example.entites.Service;
import org.example.repositories.service.ServiceRepo;
import org.example.services.baseentity.BaseEnitityServce;
import org.example.services.baseentity.BaseEntityServceImpl;

 public class ServiceServiceImpl extends BaseEntityServceImpl<Service,Long,ServiceRepo> implements ServiceService {
    ServiceRepo serviceRepo;


    public ServiceServiceImpl(ServiceRepo baseRepo) {
        super(baseRepo);
        this.serviceRepo = baseRepo;
    }


     @Override
     public Service findByName(String name) {
         return null;
     }

     @Override
     public boolean addSubService(Service parentService, Service subService) {
        //todo check if its not duplicate
         if (serviceRepo.findByName(subService.getName()) != null) {
             return false;
         }
         return serviceRepo.addSubService(parentService, subService);
     }

     @Override
     public boolean addSubService(Long parentId, Service subService) {
         return serviceRepo.addSubService(parentId, subService);
     }

     @Override
     public boolean removeSubService(Service service) {
         return false;
     }
 }
