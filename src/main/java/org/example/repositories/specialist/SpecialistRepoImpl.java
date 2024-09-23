package org.example.repositories.specialist;

import jakarta.persistence.EntityManager;
import org.example.entites.Specialist;
import org.example.repositories.baseentity.BaseEnittiyRepoImpl;

public class SpecialistRepoImpl extends BaseEnittiyRepoImpl<Specialist,Long> implements SpecialistRepo {
    public SpecialistRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Specialist> getEntityClass() {
        return Specialist.class;
    }
}
