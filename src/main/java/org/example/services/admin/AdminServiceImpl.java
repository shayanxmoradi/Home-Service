package org.example.services.admin;

import org.example.entites.*;
import org.example.entites.enums.SpecialistStatus;
import org.example.repositories.admin.AdminRepo;
import org.example.repositories.service.ServiceRepo;
import org.example.repositories.specialist.SpecialistRepo;
import org.example.services.baseentity.BaseEntityServceImpl;

import java.util.List;

public class AdminServiceImpl extends BaseEntityServceImpl<Admin,Long,AdminRepo> implements AdminService {
     SpecialistRepo specialistRepo;
     ServiceRepo serviceRepo;

    public AdminServiceImpl(AdminRepo baseRepo,  SpecialistRepo specialistRepo) {
        super(baseRepo);
        this.specialistRepo = specialistRepo;
    }
    public AdminServiceImpl(AdminRepo baseRepo,  ServiceRepo serviceRepo) {
        super(baseRepo);
        this.serviceRepo = serviceRepo;
    }

    public void saveSpecialist(Specialist specialist) {
        specialistRepo.save(specialist); // Delegate to SpecialistService
    }

    @Override
    public void deleteSpcialist(Specialist specialist) {
        specialistRepo.delelte(specialist); // Delegate to SpecialistService

    }

    @Override
    public void deleteSpcialistById(Long specialistId) {
        specialistRepo.deleteByID(specialistId);
    }

    @Override
    public List<Specialist> getAllSpecialist() {
        return specialistRepo.findAll();
    }

    @Override
    public List<Specialist> getSpecialistByStatus(SpecialistStatus status) {
        return specialistRepo.getSpecialistByStatus(status);
    }

    @Override
    public List<BaseUser> getAllUsers() {
        return List.of();
    }

    @Override
    public void changeSpecialistStatusById(Specialist specialistId, SpecialistStatus newStatus) {
    //    specialistRepo.changeSpecialistStatusById(specialistId, newStatus);
//
//        Specialist foundedUser = specialistRepo.findById(specialistId);
        specialistId.setSpecialistStatus(newStatus);
        specialistRepo.update(specialistId);
       // specialistRepo.changeSpecialistStatusById(specialistId,newStatus);
    }

    @Override
    public void addingSpecialistToSubService(Specialist specialist, Service subService) {
        serviceRepo.addingSpecialistToSubService(specialist, subService);


    }
}
