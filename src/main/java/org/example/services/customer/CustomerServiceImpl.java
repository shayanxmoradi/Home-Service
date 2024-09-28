package org.example.services.customer;

import jakarta.validation.ValidationException;
import org.example.entites.Customer;
import org.example.entites.Order;
import org.example.entites.Service;
import org.example.repositories.customer.CustomerRepo;
import org.example.repositories.order.OrderRepo;
import org.example.repositories.service.ServiceRepo;
import org.example.services.baseentity.BaseEntityServceImpl;

import javax.management.ServiceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CustomerServiceImpl extends BaseEntityServceImpl<Customer, Long, CustomerRepo> implements CustomerService {
    ServiceRepo serviceRepo;
    OrderRepo orderRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        super(customerRepo);
    }

    public CustomerServiceImpl(CustomerRepo baseRepo, OrderRepo orderRepo) {
        super(baseRepo);
        this.orderRepo = orderRepo;
    }

    public CustomerServiceImpl(CustomerRepo baseRepo, ServiceRepo serviceRepo) {
        super(baseRepo);
        this.serviceRepo = serviceRepo;
    }

    @Override
    public Optional<List<Service>> getAllFirstLayerServices() {
        return Optional.ofNullable(serviceRepo.findFirstLayerServices());
    }

    @Override
    public Order registerOrder(Customer customer, Order order) {
        if (customer!=null) {
            if (order != null) {
                customer.addOrder(order);
                order.setCustomer(customer);
                Order savedOrder = orderRepo.save(order).get();
                baseRepository.update(customer);
                return savedOrder;
            } else throw new ValidationException("customer is null");
        } else throw new ValidationException("order is null");
    }

    @Override
    public Optional<List<Order>> getCustomerOrders(Customer customer) {
        return orderRepo.findOrderOfCustomer(customer.getId());
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {

        return Optional.of(baseRepository.getCustomerByEmail(email).get());
    }

    //    @Override
//    public Customer save(Customer entity) {
//        if (getCustomerByEmail(entity.getEmail()).isPresent()) {
//            System.err.println("Customer with emailalready exists");
//            return null;
//        }
//        return super.save(entity);
//    }
    @Override
    public Optional<Customer> save(Customer entity) {
        if (baseRepository.findWithAttribute(Customer.class, "email", entity.getEmail()).get().isEmpty()) {
            return super.save(entity);

        }
        System.err.println("Customer with emailalready exists");
        return null;
    }


    public Service navigateAndSelectService() throws ServiceNotFoundException {
        List<Service> topLevelServices = serviceRepo.findFirstLayerServices();
        if (topLevelServices.isEmpty()) {
            throw new ServiceNotFoundException("No services available.");
        }

        return navigate(null, topLevelServices);
    }

    @Override
    public void updatePassword(Customer customer, String oldPassword, String newPassword) {
        Customer currentCustomer = findByEmailAndPass(customer.getEmail(), oldPassword)
                .orElseThrow(() -> new ValidationException("Incorrect password"));

        currentCustomer.setPassword(newPassword);

        baseRepository.update(currentCustomer);
    }

    @Override
    public Optional<Customer> findByEmailAndPass(String username, String password) {
        return baseRepository.findByEmailAndPass(username, password);
    }

    private Service navigate(Service parentService, List<Service> subServices) {
        if (parentService != null && !parentService.isCategory()) {
            System.out.println("You have selected the service: " + parentService.getName());
            return parentService;
        }

        if (parentService == null) {
            System.out.println("Top-level services (categories):");
        } else {
            System.out.println("Category: " + parentService.getName());
            System.out.println("Subservices:");
        }

        for (int i = 0; i < subServices.size(); i++) {
            System.out.println((i + 1) + ". " + subServices.get(i).getName());
        }

        if (subServices.size() < 1) {
            System.out.printf("no subservices available\n");
            return null;
        }
        int choice = getUserInput(subServices.size());

        Service chosenSubService = subServices.get(choice - 1);

        return navigate(chosenSubService, chosenSubService.getSubServices());
    }

    private int getUserInput(int maxChoice) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.print("Enter your choice (1-" + maxChoice + "): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number between 1 and " + maxChoice + ": ");
                scanner.next();
            }
            choice = scanner.nextInt();
        } while (choice < 1 || choice > maxChoice);

        return choice;
    }
}
