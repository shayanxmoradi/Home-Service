package org.example;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import org.example.entites.*;
import org.example.entites.enums.SpecialistStatus;
import org.example.exceptions.FileNotFoundException;
import org.example.exceptions.ImageTooLargeException;
import org.example.exceptions.ServiceAlreadyExistsException;
import org.example.repositories.customer.CustomerRepo;
import org.example.repositories.customer.CustomerRepoImpl;
import org.example.repositories.order.OrderRepo;
import org.example.repositories.order.OrderRepoImpl;
import org.example.repositories.service.ServiceRepo;
import org.example.repositories.service.ServiceRepoImpl;
import org.example.repositories.specialist.SpecialistRepo;
import org.example.repositories.specialist.SpecialistRepoImpl;
import org.example.services.customer.CustomerService;
import org.example.services.customer.CustomerServiceImpl;
import org.example.services.service.ServiceService;
import org.example.services.service.ServiceServiceImpl;
import org.example.services.admin.AdminService;
import org.example.services.admin.AdminServiceImpl;
import org.example.services.speciallist.SpeciallistService;
import org.example.services.speciallist.SpeciallistServiceImpl;
import org.example.util.ApplicationContext;

import javax.management.ServiceNotFoundException;
import java.io.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static EntityManager entityManager;

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        entityManager = applicationContext.getEntityManager();


//        ServiceRepo baseRepo = new ServiceRepoImpl(entityManager);
//        ServiceService serviceService = new ServiceServiceImpl(baseRepo);
//        deleteById(502L);
//        System.out.println("still working");
//        Service service = new Service("daftar");
//        service.setDescription("no description");
//        service.setBase_price(10220.20F);
//        //createSubService(null, service);
//        showAllByParentId(902);


        initializeServices();
        //store spefcialist

        savingSpecialist();

        //deletespecialistById(902l);

        showingAllSpecialists();

        // changeStatusOfSpecialistById(1002l, SpecialistStatus.APPROVED);


        Specialist specialist;

        SpecialistRepo baseRepox = new SpecialistRepoImpl(entityManager);

        SpeciallistService speciallistService = new SpeciallistServiceImpl(baseRepox);
        //specialist = speciallistService.findById(102l);

        //    retriveImageOfSpecialist(specialist, "/Users/shayan/Desktop/saved.jpg");


        // adding specialist to a subservice
        addingSpecialistToService(802l, 510l);


        // customerside

        showFirstLayerServices();


        createNewCustomer("shayan", "moradi", "shayan@gmail.com", "12345567898");
        Service chosenService = choseService();
        if (chosenService != null) {
            registerOrder(222, chosenService.getId(), 803l);

        }


        System.out.println("customer orders");


        getOrderOfCustomer(102l)
                .orElse(Collections.emptyList())
                .forEach(System.out::println);


    }

    private static Service choseService() {
        ServiceRepo serviceRepo = new ServiceRepoImpl(entityManager);
        CustomerService customerService = new CustomerServiceImpl(null, serviceRepo);
        Service selectedService = null;
        try {
            selectedService = customerService.navigateAndSelectService();
        } catch (ServiceNotFoundException e) {
            System.out.println("NOTHING FOUND");
            return null;
        }
        try {

            System.out.println("Final Selected Service: " + selectedService.getName());
            return selectedService;
        } catch (Exception e) {

        }
        return null;
    }

    private static Optional<List<Order>> getOrderOfCustomer(long l) {
        CustomerRepo customerRepo = new CustomerRepoImpl(entityManager);
        OrderRepo orderRepo = new OrderRepoImpl(entityManager);
        CustomerService customerService = new CustomerServiceImpl(customerRepo, orderRepo);
        if (getCustomerById(l).isEmpty()) {
            System.out.println("no customer found");
            return Optional.empty();
        }

        return customerService.getCustomerOrders(getCustomerById(l).get());

//        if (customerService.getCustomerOrders(getCustomerById(l)).isEmpty()) {
//            System.err.println("customer orders not found");
//            return null;
        //  }

        //   return customerService.getCustomerOrders(getCustomerById(l)).get();
    }

    private static void registerOrder(float offredPrice, Long serviceId, Long customerId) {
        Order order = new Order();
        order.setOrderDescription("test");
        Address address = new Address();
        address.setStreet("street");
        address.setCity("city");
        address.setState("state");
        address.setZip("zip");
        order.setAddress(address);

        ServiceRepo baseRepo = new ServiceRepoImpl(entityManager);
        ServiceService serviceService = new ServiceServiceImpl(baseRepo);
        Optional<Service> serviceByName = serviceService.findById(serviceId);
        if (serviceByName.isEmpty()) {
            System.out.println("no service found");
            return;
        }
        order.setChoosenService(serviceByName.get());
        Float basePrice = serviceByName.get().getBase_price();
        if (basePrice > offredPrice) {
            System.out.printf("offred price is smaller than base price");
            return;
        }


        order.setOfferedPrice(2222.2);
        order.setServiceTime(Time.valueOf(LocalTime.now()));
        order.setServiceDate(Date.valueOf(LocalDate.now()));


        OrderRepo opRepo = new OrderRepoImpl(entityManager);
        CustomerRepo cRepo = new CustomerRepoImpl(entityManager);
        CustomerService customerService = new CustomerServiceImpl(cRepo, opRepo);

        Optional<Customer> chosenCustomerOpt = customerService.findById(customerId);

        if (chosenCustomerOpt.isEmpty()) {
            System.out.println("Customer with  this ID  not found.");
            return;
        }
        Customer choesnCustomer = customerService.findById(customerId).get();


        customerService.registerOrder(choesnCustomer, order);

    }

    public static Optional<Customer> getCustomerById(long id) {
        return Optional.ofNullable(entityManager.find(Customer.class, id));
    }

    private static void showFirstLayerServices() {
        ServiceRepo serviceRepo = new ServiceRepoImpl(entityManager);
        CustomerRepo customerRepo = new CustomerRepoImpl(entityManager);
        CustomerService customerService = new CustomerServiceImpl(customerRepo, serviceRepo);
        System.out.printf("after here");
        if (customerService.getAllFirstLayerServices().isEmpty()) {
            System.out.println("no service found");
            return;
        }
        System.out.println(customerService.getAllFirstLayerServices());
    }

    private static void addingSpecialistToService(long specialistId, long subServiceId) {
        SpecialistRepo baseRepo = new SpecialistRepoImpl(entityManager);

        Optional<Specialist> specialist = baseRepo.findById(specialistId);
        if (specialist.isEmpty()) {
            System.out.println("no specialist found");
            return;
        }

        ServiceRepo serviceRepo = new ServiceRepoImpl(entityManager);
        Optional<Service> service = serviceRepo.findById(subServiceId);
        if (service.isEmpty()) {
            System.out.printf("service not found");
            return;
        }
        AdminService adminService = new AdminServiceImpl(null, serviceRepo);
        adminService.addingSpecialistToSubService(specialist.orElse(null), service.orElse(null));

    }

    private static void changeStatusOfSpecialistById(long l, SpecialistStatus status) {
        SpecialistRepo baseRepo = new SpecialistRepoImpl(entityManager);


        AdminService adminService = new AdminServiceImpl(null, baseRepo);
        adminService.changeSpecialistStatusById(baseRepo.findById(l).get(), status);
    }

    private static void showingAllSpecialists() {
        SpecialistRepo baseRepo = new SpecialistRepoImpl(entityManager);


        AdminService adminService = new AdminServiceImpl(null, baseRepo);
        System.out.println(adminService.getAllSpecialist());
    }

    private static void deletespecialistById(long l) {
        SpecialistRepo baseRepo = new SpecialistRepoImpl(entityManager);


        AdminService adminService = new AdminServiceImpl(null, baseRepo);
        adminService.deleteSpcialistById(l);
    }

    private static void savingSpecialist() {
        Specialist specialist = new Specialist();
        specialist.setFirstName("shayanh");
        specialist.setLastName("shayanh");
        specialist.setPassword("123123");
        try {

            specialist.setEmail("shayanZSxhzs@gmail.com");// checks duplicate

            specialist.setSpecialistStatus(SpecialistStatus.NEW);


            Specialist specialistWithImage = savingImageToSpecialist(specialist, "/Users/shayan/Desktop/x.jpg");

            if (specialistWithImage == null) {
                System.out.printf("no image found");
            } else {
                saveSpecialist(specialistWithImage);
            }
        } catch (EntityExistsException e) {
            System.out.printf("duplicate email");
            return;
        }

    }

    private static Specialist savingImageToSpecialist(Specialist specialist, String filePath) {
        //todo move to service
        //todo jpg format check
        try {
            File imageFile = new File(filePath);

            if (!imageFile.exists()) {
                throw new org.example.exceptions.FileNotFoundException("Image file not found at the  path.", filePath);
            }

            if (imageFile.length() > 300 * 1024) {
                throw new ImageTooLargeException("Image exceeds 300KB, cannot store it.");
            }

            FileInputStream fileInputStream = new FileInputStream(imageFile);
//            fileInputStream.readAllBytes();
            byte[] imageData = new byte[(int) imageFile.length()];
            fileInputStream.read(imageData);
            fileInputStream.close();

            specialist.setPersonalImage(imageData);
            System.out.println("Image saved successfully!");
            return specialist;

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage() + ": \n" + e.getFilePath());
        } catch (ImageTooLargeException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("An IO error occurred while processing the image.");
            e.printStackTrace();
        }

        return null;
    }

    private static void retriveImageOfSpecialist(Specialist specialist, String savingPath) {
        try {

            byte[] imageData = specialist.getPersonalImage();

            FileOutputStream fileOutputStream = new FileOutputStream(savingPath);
            fileOutputStream.write(imageData);
            fileOutputStream.close();

            System.out.println("Image retrieved and saved to file successfully!");


        } catch (IOException e) {
            System.err.println("An IO error occurred while processing the image.");
        }
    }

    private static void saveSpecialist(Specialist specialist) {
        SpecialistRepo baseRepo = new SpecialistRepoImpl(entityManager);


        AdminService adminService = new AdminServiceImpl(null, baseRepo);
        try {

            adminService.saveSpecialist(specialist);
        } catch (EntityExistsException e) {
            System.out.printf(e.getMessage());
        }
    }

    private static void showAllByParentId(long l) {
        ServiceRepo baseRepo = new ServiceRepoImpl(entityManager);
        ServiceService serviceService = new ServiceServiceImpl(baseRepo);
        System.out.println(serviceService.findAllByParentId(l));
    }

    public static void createSubService(Long parentService, Service subService) {
        ServiceRepo baseRepo = new ServiceRepoImpl(entityManager);
        ServiceService serviceService = new ServiceServiceImpl(baseRepo);

        try {
            if (serviceService.addSubService(parentService, subService) == true) {

                System.out.println("Subservice added successfully.");
            }
        } catch (ServiceAlreadyExistsException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void deleteById(Long id) {

        ServiceRepo baseRepo = new ServiceRepoImpl(entityManager);
        ServiceService serviceService = new ServiceServiceImpl(baseRepo);
        try {
            serviceService.deleteById(id);
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void initializeServices() {
        Service building = new Service();
        building.setName("Building");
        building.setDescription("Building related services");
        building.setCategory(true);

        Service vehicles = new Service();
        vehicles.setName("Vehicles");
        vehicles.setDescription("Vehicle-related services");
        vehicles.setCategory(true);

        Service moving = new Service();
        moving.setName("Moving");
        moving.setDescription("Moving and shipping services");
        moving.setCategory(true);

        // Subservices for Home Appliances
        Service homeAppliances = new Service();
        homeAppliances.setName("Home Appliances");
        homeAppliances.setDescription("Home appliances services");
        homeAppliances.setCategory(true);

        Service kitchenAppliances = new Service();
        kitchenAppliances.setName("Kitchen Appliances");
        kitchenAppliances.setDescription("Kitchen appliances repair");
        kitchenAppliances.setBase_price(100f);
        kitchenAppliances.setCategory(false);

        Service laundry = new Service();
        laundry.setName("Laundry Appliances");
        laundry.setDescription("Laundry appliances repair");
        laundry.setBase_price(120f);
        laundry.setCategory(false);

        homeAppliances.addSubService(kitchenAppliances);
        homeAppliances.addSubService(laundry);

        // Subservices for Cleaning and Hygiene
        Service cleaning = new Service();
        cleaning.setName("Cleaning");
        cleaning.setDescription("Cleaning and hygiene services");
        cleaning.setCategory(true);

        Service dryCleaning = new Service();
        dryCleaning.setName("Dry Cleaning");
        dryCleaning.setDescription("Professional dry cleaning");
        dryCleaning.setBase_price(80f);
        dryCleaning.setCategory(false);

        Service carpetCleaning = new Service();
        carpetCleaning.setName("Carpet Cleaning");
        carpetCleaning.setDescription("Carpet and upholstery cleaning");
        carpetCleaning.setBase_price(90f);
        carpetCleaning.setCategory(false);

        cleaning.addSubService(dryCleaning);
        cleaning.addSubService(carpetCleaning);


        ServiceRepo serviceRepo = new ServiceRepoImpl(entityManager);
        ServiceService serviceService = new ServiceServiceImpl(serviceRepo);
        serviceService.save(building);
        serviceService.save(vehicles);
        serviceService.save(moving);
        serviceService.save(homeAppliances);
        serviceService.save(cleaning);

        System.out.println("Services have been initialized and saved successfully.");
    }

    private static void createNewCustomer(String customerName, String customerLastName, String customerEmail, String customerPass) {
        Customer customer = new Customer();
        customer.setFirstName(customerName);
        customer.setLastName(customerLastName);
        customer.setEmail(customerEmail);
        customer.setRegistrationDate(Date.valueOf(LocalDate.now()));
        customer.setRegistrationTime(Time.valueOf(LocalTime.now()));
        customer.setPassword(customerPass);

        CustomerRepo customerRepo = new CustomerRepoImpl(entityManager);
        CustomerService customerService = new CustomerServiceImpl(customerRepo);
        customerService.save(customer);

    }

}