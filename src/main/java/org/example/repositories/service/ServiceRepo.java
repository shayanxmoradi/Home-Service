package org.example.repositories.service;

import org.example.entites.Service;
import org.example.entites.Specialist;
import org.example.repositories.baseentity.BaseEnitityRepo;

import java.util.List;
import java.util.Optional;

public interface ServiceRepo extends BaseEnitityRepo<Service,Long> {
    Optional<Service> findByName(String name);
    boolean addSubService(Service parentService, Service subService);
    boolean addSubService(Long parentId, Service subService);
    boolean removeSubService(Service service);
    List<Service> findAllByParentId(Long parentId);
    void addingSpecialistToSubService(Specialist specialist, Service subService);

}
