package org.example.repositories.customer;

import jakarta.persistence.EntityManager;
import org.example.entites.Customer;
import org.example.entites.Order;
import org.example.repositories.baseentity.BaseEnittiyRepoImpl;
import org.example.repositories.order.OrderRepo;

public class CustomerRepoImpl extends BaseEnittiyRepoImpl<Customer, Long> implements CustomerRepo {

    public CustomerRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }


}
