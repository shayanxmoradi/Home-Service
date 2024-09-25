package org.example.services.customer;

import org.example.entites.Customer;
import org.example.entites.Order;
import org.example.entites.Service;
import org.example.services.baseentity.BaseEnitityServce;

import java.util.List;
import java.util.Optional;

public interface CustomerService extends BaseEnitityServce<Customer,Long> {
    //todo
    public List<Service> getAllFirstLayerServices();
    Order registerOrder(Customer customer,Order order);
    Optional<List<Order>>getCustomerOrders(Customer customer);
    Optional<Customer> getCustomerByEmail(String email);

}
