package com.solvd.repairService.views;

import com.solvd.repairService.DAO.*;
import com.solvd.repairService.helpers.calculateData.Calculate;
import com.solvd.repairService.helpers.calculateData.Global;
import com.solvd.repairService.model.*;
import com.solvd.repairService.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerProfileView {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }
    private final static Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(CustomerProfileView.class);
    private static CustomerProfilesService serviceCP = Global.state()
            ? new CustomerProfilesService(new CustomerProfilesDAO())
            : new CustomerProfilesService(new CustomerProfilesDAO());
    private static OrdersService serviceO = Global.state()
            ? new OrdersService(new OrdersDAO())
            : new OrdersService(new OrdersDAO());

    private static EquipmentsService serviceE = Global.state()
            ? new EquipmentsService(new EquipmentsDAO())
            : new EquipmentsService(new EquipmentsDAO());

    private static ServiceCentersService serviceSC = Global.state()
            ? new ServiceCentersService(new ServiceCentersDAO())
            : new ServiceCentersService(new ServiceCentersDAO());
    private static OrderExecutionsService serviceOE = Global.state()
            ? new OrderExecutionsService(new OrderExecutionsDAO())
            : new OrderExecutionsService(new OrderExecutionsDAO());
    private static EmployerProfileService serviceEP = Global.state()
            ? new EmployerProfileService(new EmployerProfilesDAO())
            : new EmployerProfileService(new EmployerProfilesDAO());

    private static ProblemService serviceP= Global.state()
            ? new ProblemService(new ProblemsDAO())
            : new ProblemService(new ProblemsDAO());

    private static EquipmentProblemService serviceEqPr= Global.state()
            ? new EquipmentProblemService(new EquipmentProblemDAO())
            : new EquipmentProblemService(new EquipmentProblemDAO());

    public static void profileUI(Users user) {

        CustomerProfiles profile = null;

        var isDetected = serviceCP.checkAvailability(new CustomerProfiles(user.id()));
        profile = isDetected ? serviceCP.selectById(user.id())
                : serviceCP.create(new CustomerProfiles(user.id()));
        profile.user(user);
        LOGGER.info("nickname: " + profile.nick());
        LOGGER.info("phone: " + profile.phone());
        LOGGER.info("email: " + user.login());

        System.out.print("\n");
        LOGGER.info("1: profile");
        LOGGER.info("2: orders");
        String answer = scanner.nextLine();
        switch (answer) {
            case "1":
                profileActions(profile);
                break;
            case "2":
                ordersActions(profile);
                break;
            default:profileUI(user);
        }
    }

    private static void ordersActions(CustomerProfiles profile) {
        LOGGER.debug("1: create new order");
        LOGGER.debug("2: check order history");
        LOGGER.debug("3: return");

        String answer = scanner.nextLine();
        switch (answer) {
            case "1":
                createNewOrder(profile);
                break;
            case "2":
                checkOrderHistory(profile);
                break;
            case "3":
                profileUI(profile.user());
                break;
            default:
                ordersActions(profile);
        }
    }

    private static void createNewOrder(CustomerProfiles profile) {
        LOGGER.debug("Type: ");
        String type = scanner.nextLine();
        LOGGER.debug("Producer: ");
        String producer = scanner.nextLine();
        LOGGER.debug("Model: ");
        String model = scanner.nextLine();
        LOGGER.debug("Price: ");

        double price = scanner.nextDouble();
        var equipment = new Equipments(type, producer, model, price);
        try {
            equipment = serviceE.create(equipment);
        } catch (Exception e) {
            LOGGER.error(e);
            ordersActions(profile);
        }
        var center = serviceSC.findUnoccupied();
        var employee = serviceEP.findByServiceCenter(center);
        var problem = serviceP.create();
        equipment.addProblem(problem);


        var ep = serviceEqPr.create(equipment, problem);

        var orderExecution = serviceOE.create(equipment, employee, center);


        serviceO.create(profile, equipment, orderExecution);
        LOGGER.info("Order was successfully created");
        ordersActions(profile);

    }
    private static void checkOrderHistory(CustomerProfiles profile) {
        var orders = serviceO.ordersHistory(profile);
        if (orders != null) {
            LOGGER.info(orders.size() + " of Orders");
            for (var element : orders) {
                LOGGER.info(element);
            }
        }
    }

    private static void profileActions(CustomerProfiles profile) {
        LOGGER.debug("1: change nickname");
        LOGGER.debug("2: change phone");
        LOGGER.debug("3: advanced settings");
        String answer = scanner.nextLine();
        switch (answer) {
            case "1":
                changeNick(profile);
                break;
            case "2":
                changePhone(profile);
                break;
            case "3":
                advancedSettingsUI(profile);
                break;
            default:
                profileUI(profile.user());
        }
    }

    private static void advancedSettingsUI(CustomerProfiles profile) {
        UserSettingsView.settingsUI(profile);
    }

    private static void changePhone(CustomerProfiles profile) {
        CustomerProfiles newProfile = new CustomerProfiles(profile.id(), profile.nick(), profile.phone());
        LOGGER.debug("Enter new phone");
        newProfile.phone(scanner.nextLine());
        try {
            profile.phone(serviceCP.updateProfile(profile, newProfile).phone());

        } catch (Exception e) {
            LOGGER.error("same phone");
        }
        profileActions(profile);
    }

    private static void changeNick(CustomerProfiles profile) {
        CustomerProfiles newProfile = new CustomerProfiles(profile);
        LOGGER.debug("Enter new nick");
        newProfile.nick(scanner.nextLine());
        CustomerProfilesService service = new CustomerProfilesService(new CustomerProfilesDAO());
        try {
            profile.nick((service.updateProfile(profile, newProfile)).nick());
        } catch (Exception e) {
            LOGGER.error("same nick");
        }
        profileActions(profile);
    }

}
