package org.example.services.service.admin;

import org.example.entites.Admin;
import org.example.entites.Specialist;
import org.example.repositories.admin.AdminRepo;
import org.example.repositories.specialist.SpecialistRepo;
import org.example.services.baseentity.BaseEntityServceImpl;
import org.example.services.speciallist.SpeciallistService;

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


}
