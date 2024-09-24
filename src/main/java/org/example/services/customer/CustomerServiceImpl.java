package org.example.services.customer;

import org.example.entites.Customer;
import org.example.entites.Order;
import org.example.entites.Service;
import org.example.repositories.customer.CustomerRepo;
import org.example.repositories.order.OrderRepo;
import org.example.repositories.service.ServiceRepo;
import org.example.services.baseentity.BaseEntityServceImpl;

import java.util.List;

public class CustomerServiceImpl extends BaseEntityServceImpl<Customer,Long, CustomerRepo> implements CustomerService {
    ServiceRepo serviceRepo;
    OrderRepo orderRepo;

    public CustomerServiceImpl(CustomerRepo baseRepo) {
        super(baseRepo);
    } public CustomerServiceImpl(CustomerRepo baseRepo,OrderRepo orderRepo) {
        super(baseRepo);
        this.orderRepo = orderRepo;
    }
    public CustomerServiceImpl(CustomerRepo baseRepo, ServiceRepo serviceRepo) {
        super(baseRepo);
        this.serviceRepo = serviceRepo;
    }

    @Override
    public List<Service> getAllFirstLayerServices() {
      return   serviceRepo.findFirstLayerServices();
    }

    @Override
    public Order registerOrder(Customer customer,Order order) {
        customer.addOrder(order);
        order.setCustomer(customer);
        Order savedOrder = orderRepo.save(order);
        baseRepository.update(customer);
    return savedOrder;
    }

    @Override
    public List<Order> getCustomerOrders(Customer customer) {
        return orderRepo.findOrderOfCustomer(customer.getId());
    }
}
