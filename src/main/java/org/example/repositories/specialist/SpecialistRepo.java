package org.example.repositories.specialist;

import org.example.entites.Specialist;
import org.example.entites.enums.SpecialistStatus;
import org.example.repositories.baseentity.BaseEnitityRepo;

import java.util.List;

public interface SpecialistRepo extends BaseEnitityRepo<Specialist,Long> {
    List<Specialist> getSpecialistByStatus(SpecialistStatus status);

    void changeSpecialistStatusById(Long specialistId, SpecialistStatus newStatus);


}
