package org.example.repositories.customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entites.Customer;
import org.example.entites.Order;
import org.example.entites.Service;
import org.example.repositories.baseentity.BaseEnittiyRepoImpl;
import org.example.repositories.order.OrderRepo;

import java.util.List;
import java.util.Optional;

public class CustomerRepoImpl extends BaseEnittiyRepoImpl<Customer, Long> implements CustomerRepo {

    public CustomerRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }


    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        TypedQuery<Customer> query = entityManager.createQuery("select c  from Customer  c where c.email = :email", Customer.class);
        query.setParameter("email", email);
        Customer customers = query.getSingleResult();
        return Optional.of(customers);

    }

    @Override
    public Optional<Customer> findByEmailAndPass(String email, String password) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.email = :email AND c.password = :password",getEntityClass());
        query.setParameter("email", email);
        query.setParameter("password", password);

        return Optional.ofNullable(query.getResultStream().findFirst().get());
    }
}
