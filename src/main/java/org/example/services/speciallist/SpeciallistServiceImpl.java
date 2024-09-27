package org.example.services.speciallist;

import org.example.entites.Customer;
import org.example.entites.Specialist;
import org.example.repositories.specialist.SpecialistRepo;
import org.example.services.baseentity.BaseEnitityServce;
import org.example.services.baseentity.BaseEntityServceImpl;

import java.util.Optional;

public class SpeciallistServiceImpl extends BaseEntityServceImpl<Specialist,Long,SpecialistRepo> implements SpeciallistService {
    SpecialistRepo specialistRepo;
    public SpeciallistServiceImpl(SpecialistRepo baseRepo) {
        super(baseRepo);
        this.specialistRepo = baseRepo;
    }

    @Override
    public Optional<Specialist> save(Specialist entity) {
        if ( baseRepository.findWithAttribute(Specialist.class, "email", entity.getEmail()).get().isEmpty()) {
            return super.save(entity);

        }
        System.err.println("Customer with emailalready exists");
        return null;
    }

}
