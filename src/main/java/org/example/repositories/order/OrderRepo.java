package org.example.repositories.order;

import org.example.entites.Order;
import org.example.repositories.baseentity.BaseEnitityRepo;

import java.util.List;

public interface OrderRepo extends BaseEnitityRepo<Order,Long> {

    List<Order> findOrderOfCustomer(Long id);
}
