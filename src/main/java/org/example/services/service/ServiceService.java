package org.example.services.service;

import org.example.entites.Service;
import org.example.services.baseentity.BaseEnitityServce;

import java.util.List;
import java.util.Optional;

public interface ServiceService extends BaseEnitityServce<Service,Long> {
    Optional<Service> findByName(String name);
    boolean addSubService(Long parentId, Service subService);
    boolean removeSubService(Service service);
    List<Service> findAllByParentId(Long parentId);

}
