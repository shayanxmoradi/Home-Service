package org.example.services.admin;

import org.example.entites.*;
import org.example.entites.enums.SpecialistStatus;
import org.example.services.baseentity.BaseEnitityServce;

import java.util.List;

public interface AdminService extends BaseEnitityServce<Admin, Long> {
    public void saveSpecialist(Specialist specialist);

    public void deleteSpcialist(Specialist specialist);

    public void deleteSpcialistById(Long specialistId);

    public List<Specialist> getAllSpecialist();

    public List<Specialist> getSpecialistByStatus(SpecialistStatus status);

    public List<BaseUser> getAllUsers();

    public void changeSpecialistStatusById(Specialist specialistId, SpecialistStatus newStatus);

    public void addingSpecialistToSubService(Specialist specialist, Service subService);
}
