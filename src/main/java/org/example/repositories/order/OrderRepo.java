package org.example.repositories.order;

import org.example.entites.Order;
import org.example.repositories.baseentity.BaseEnitityRepo;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends BaseEnitityRepo<Order,Long> {

    Optional<List<Order>> findOrderOfCustomer(Long id);
}
