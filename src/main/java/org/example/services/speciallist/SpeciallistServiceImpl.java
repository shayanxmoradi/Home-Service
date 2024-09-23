package org.example.services.speciallist;

import org.example.entites.Specialist;
import org.example.repositories.specialist.SpecialistRepo;
import org.example.services.baseentity.BaseEnitityServce;
import org.example.services.baseentity.BaseEntityServceImpl;

public class SpeciallistServiceImpl extends BaseEntityServceImpl<Specialist,Long,SpecialistRepo> implements SpeciallistService {
    SpecialistRepo specialistRepo;
    public SpeciallistServiceImpl(SpecialistRepo baseRepo) {
        super(baseRepo);
        this.specialistRepo = baseRepo;
    }
    //todo exists by email
}
