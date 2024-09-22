package org.example.repositories.service;

import org.example.entites.Service;
import org.example.repositories.baseentity.BaseEnitityRepo;

public interface ServiceRepo extends BaseEnitityRepo<Service,Long> {
    Service findByName(String name);
    boolean addSubService(Service parentService, Service subService);
    boolean addSubService(Long parentId, Service subService);
    boolean removeSubService(Service service);
}
