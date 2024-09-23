package org.example.services.service.admin;

import org.example.entites.Admin;
import org.example.entites.Service;
import org.example.entites.Specialist;
import org.example.services.baseentity.BaseEnitityServce;

import java.util.List;
import java.util.Optional;

public interface AdminService extends BaseEnitityServce<Admin,Long> {
    public void saveSpecialist(Specialist specialist);
    public void deleteSpcialist (Specialist specialist);
    public void deleteSpcialistById (Long specialistId);

}
