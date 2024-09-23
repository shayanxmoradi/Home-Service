package org.example;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import org.example.entites.*;
import org.example.exceptions.FileNotFoundException;
import org.example.exceptions.ImageTooLargeException;
import org.example.exceptions.ServiceAlreadyExistsException;
import org.example.repositories.service.ServiceRepo;
import org.example.repositories.service.ServiceRepoImpl;
import org.example.repositories.specialist.SpecialistRepo;
import org.example.repositories.specialist.SpecialistRepoImpl;
import org.example.services.service.ServiceService;
import org.example.services.service.ServiceServiceImpl;
import org.example.services.admin.AdminService;
import org.example.services.admin.AdminServiceImpl;
import org.example.services.speciallist.SpeciallistService;
import org.example.services.speciallist.SpeciallistServiceImpl;
import org.example.util.ApplicationContext;

import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static EntityManager entityManager;

    public static void main(String[] args) throws IOException {

        ApplicationContext applicationContext = ApplicationContext.getInstance();
        entityManager = applicationContext.getEntityManager();


        ServiceRepo baseRepo = new ServiceRepoImpl(entityManager);
        ServiceService serviceService = new ServiceServiceImpl(baseRepo);
        deleteById(502L);
        System.out.println("still working");
        Service service = new Service("daftar");
        service.setDescription("no description");
        service.setBase_price(10220.20F);
        //createSubService(null, service);
        showAllByParentId(902);


        //store spefcialist
      //  savingSpecialist();
        //deletespecialistById(902l);

        showingAllSpecialists();

       // changeStatusOfSpecialistById(1002l, SpecialistStatus.APPROVED);


        Specialist specialist;

        SpecialistRepo baseRepox = new SpecialistRepoImpl(entityManager);

        SpeciallistService speciallistService = new SpeciallistServiceImpl(baseRepox);
        //specialist = speciallistService.findById(102l);

        //    retriveImageOfSpecialist(specialist, "/Users/shayan/Desktop/saved.jpg");


        // adding specialist to a subservice
       addingSpecialistToService(1l, 2l);


    }

    private static void addingSpecialistToService(long specialistId, long subServiceId) {
        SpecialistRepo baseRepo = new SpecialistRepoImpl(entityManager);
        Specialist specialist = baseRepo.findById(specialistId);

        ServiceRepo serviceRepo = new ServiceRepoImpl(entityManager);
        Service service = serviceRepo.findById(subServiceId);

        AdminService adminService = new AdminServiceImpl(null, serviceRepo);
        adminService.addingSpecialistToSubService(specialist, service);

    }

    private static void changeStatusOfSpecialistById(long l, SpecialistStatus status) {
        SpecialistRepo baseRepo = new SpecialistRepoImpl(entityManager);


        AdminService adminService = new AdminServiceImpl(null, baseRepo);
        adminService.changeSpecialistStatusById(baseRepo.findById(l), status);
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
        try {
            File imageFile = new File(filePath);

            if (!imageFile.exists()) {
                throw new org.example.exceptions.FileNotFoundException("Image file not found at the  path.", filePath);
            }

            if (imageFile.length() > 300 * 1024) {
                throw new ImageTooLargeException("Image exceeds 300KB, cannot store it.");
            }

            FileInputStream fileInputStream = new FileInputStream(imageFile);
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

            // Save the image data to a file
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
        //todo hirarechy is not shown just showing all childerns!
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
            // Handle the exception, e.g., inform the user
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