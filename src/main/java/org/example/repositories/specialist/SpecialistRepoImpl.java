package org.example.repositories.specialist;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.entites.Service;
import org.example.entites.Specialist;
import org.example.entites.SpecialistStatus;
import org.example.repositories.baseentity.BaseEnittiyRepoImpl;

import java.util.List;


public class SpecialistRepoImpl extends BaseEnittiyRepoImpl<Specialist,Long> implements SpecialistRepo {
    public SpecialistRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Specialist> getEntityClass() {
        return Specialist.class;
    }

    @Override
    public List<Specialist> getSpecialistByStatus(SpecialistStatus status) {
        TypedQuery<Specialist> query = entityManager.createQuery("SELECT s FROM Specialist s WHERE s.specialistStatus = :specialistType", Specialist.class);
        query.setParameter("specialistType", status);
        return query.getResultList();
    }

    @Override
    public void changeSpecialistStatusById(Long specialistId, SpecialistStatus newStatus) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            // Start transaction if it's not already active
            if (!transaction.isActive()) {
                transaction.begin();
            }

            // Find the specialist
            Specialist foundedUser = findById(specialistId);

            if (foundedUser != null) {
                // Set the new status
                foundedUser.setSpecialistStatus(newStatus);

                // Update the specialist with the new status
                update(foundedUser);

                // Commit the transaction
                transaction.commit();
            } else {
                System.out.println("Specialist not found.");
            }
        } catch (Exception e) {
            // Rollback if there's an issue
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Re-throw the exception after rollback
        }}


}
