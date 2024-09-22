package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entites.Customer;
import org.example.entites.Service;
import org.example.repositories.service.ServiceRepo;
import org.example.repositories.service.ServiceRepoImpl;
import org.example.services.service.ServiceService;
import org.example.services.service.ServiceServiceImpl;
import org.example.util.ApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static EntityManager entityManager;
    public static void main(String[] args) {

        ApplicationContext applicationContext = ApplicationContext.getInstance();
        entityManager = applicationContext.getEntityManager();
//        createNewCustomer();

//        Service service = new Service();
//        service.setName("cleaning");
//
//
//        Service subService = new Service();
//        subService.setName("home-service");
//        service.addSubService(subService);
//
//        entityManager.getTransaction().begin();
//        entityManager.persist(service);
//        entityManager.getTransaction().commit();

//        createService();
        ServiceRepo baseRepo= new ServiceRepoImpl(entityManager);
        ServiceService serviceService = new ServiceServiceImpl(baseRepo);
//       serviceService.save(new Service("parent"));

//        Service parent = new Service("child");
//        parent.setId(2L);
//        parent.setParent(1L);
        Service subService = new Service("grand child");

        serviceService.addSubService(2L, subService);


    }

    private static void createService() {
        entityManager.getTransaction().begin();

        // Main service: Building Decoration
        Service buildingDecoration = new Service("Building Decoration");
        Service interiorDesign = new Service("Interior Design");
        Service exteriorDesign = new Service("Exterior Design");
        buildingDecoration.addSubService(interiorDesign);
        buildingDecoration.addSubService(exteriorDesign);

        // Main service: Building Installations
        Service buildingInstallations = new Service("Building Installations");
        Service plumbing = new Service("Plumbing");
        Service electrical = new Service("Electrical Systems");
        buildingInstallations.addSubService(plumbing);
        buildingInstallations.addSubService(electrical);

        // Main service: Transportation
        Service transportation = new Service("Transportation");
        Service movingServices = new Service("Moving Services");
        Service freightServices = new Service("Freight Services");
        transportation.addSubService(movingServices);
        transportation.addSubService(freightServices);

        // Main service: Household Appliances
        Service householdAppliances = new Service("Household Appliances");
        Service kitchenAppliances = new Service("Kitchen Appliances");
        Service laundryAppliances = new Service("Laundry Appliances");
        Service audioVisual = new Service("Audio and Visual Appliances");
        householdAppliances.addSubService(kitchenAppliances);
        householdAppliances.addSubService(laundryAppliances);
        householdAppliances.addSubService(audioVisual);

        // Main service: Cleaning and Hygiene
        Service cleaningHygiene = new Service("Cleaning and Hygiene");
        Service generalCleaning = new Service("General Cleaning");
        Service dryCleaning = new Service("Dry Cleaning");
        Service carpetCleaning = new Service("Carpet and Upholstery Cleaning");
        Service pestControl = new Service("Pest Control");
        cleaningHygiene.addSubService(generalCleaning);
        cleaningHygiene.addSubService(dryCleaning);
        cleaningHygiene.addSubService(carpetCleaning);
        cleaningHygiene.addSubService(pestControl);

        // Add other main services with subservices as needed
        // For example:
        // Main service: Vehicle Services
        Service vehicleServices = new Service("Vehicle Services");
        Service carRepair = new Service("Car Repair");
        Service carWash = new Service("Car Wash");
        vehicleServices.addSubService(carRepair);
        vehicleServices.addSubService(carWash);

        // Persist all main services (subservices will be persisted automatically)
        entityManager.persist(buildingDecoration);
        entityManager.persist(buildingInstallations);
        entityManager.persist(transportation);
        entityManager.persist(householdAppliances);
        entityManager.persist(cleaningHygiene);
        entityManager.persist(vehicleServices);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    private static void createNewCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setPassword("1234");

        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
    }

}