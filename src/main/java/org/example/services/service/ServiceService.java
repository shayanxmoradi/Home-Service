package org.example.services.service;

import org.example.entites.Service;
import org.example.services.baseentity.BaseEnitityServce;

public interface ServiceService extends BaseEnitityServce<Service,Long> {
    Service findByName(String name);
    boolean addSubService(Service parentService, Service subService);
    boolean addSubService(Long parentId, Service subService);
    boolean removeSubService(Service service);
}
