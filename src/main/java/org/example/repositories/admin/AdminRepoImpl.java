package org.example.repositories.admin;

import jakarta.persistence.EntityManager;
import org.example.entites.Admin;
import org.example.repositories.baseentity.BaseEnittiyRepoImpl;

public class AdminRepoImpl extends BaseEnittiyRepoImpl<Admin,Long> implements AdminRepo {
    public AdminRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Admin> getEntityClass() {
        return Admin.class;
    }
}
