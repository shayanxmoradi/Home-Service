package org.example.services.admin;

import org.example.entites.Admin;
import org.example.entites.BaseUser;
import org.example.entites.Specialist;
import org.example.entites.SpecialistStatus;
import org.example.repositories.admin.AdminRepo;
import org.example.repositories.specialist.SpecialistRepo;
import org.example.services.baseentity.BaseEntityServceImpl;

import java.util.List;

public class AdminServiceImpl extends BaseEntityServceImpl<Admin,Long,AdminRepo> implements AdminService {
    final SpecialistRepo specialistRepo;

    public AdminServiceImpl(AdminRepo baseRepo,  SpecialistRepo specialistRepo) {
        super(baseRepo);
        this.specialistRepo = specialistRepo;
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
}
