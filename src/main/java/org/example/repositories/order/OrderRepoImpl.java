package org.example.repositories.order;

import jakarta.persistence.EntityManager;
import org.example.entites.Order;
import org.example.repositories.baseentity.BaseEnittiyRepoImpl;

public class OrderRepoImpl extends BaseEnittiyRepoImpl<Order,Long> implements OrderRepo {

    public OrderRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Order> getEntityClass() {
        return Order.class;
    }
}
