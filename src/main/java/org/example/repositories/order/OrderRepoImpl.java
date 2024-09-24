package org.example.repositories.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entites.Order;
import org.example.repositories.baseentity.BaseEnittiyRepoImpl;

import java.util.List;

public class OrderRepoImpl extends BaseEnittiyRepoImpl<Order, Long> implements OrderRepo {

    public OrderRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Order> getEntityClass() {
        return Order.class;
    }

    @Override
    public List<Order> findOrderOfCustomer(Long id) {
        TypedQuery<Order> query = entityManager.createQuery("SELECT o From Order  o where o.customer.id = :customer_id", Order.class);
        query.setParameter("customer_id", id);
        return query.getResultList();
    }
}
