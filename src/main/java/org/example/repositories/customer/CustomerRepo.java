package org.example.repositories.customer;

import org.example.entites.Customer;
import org.example.entites.Order;
import org.example.repositories.baseentity.BaseEnitityRepo;

import java.util.Optional;

public interface CustomerRepo extends BaseEnitityRepo<Customer,Long> {

    Optional<Customer> getCustomerByEmail(String email);
}
