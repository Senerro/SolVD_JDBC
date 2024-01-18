package com.solvd.repairService.helpers;

import com.solvd.repairService.model.*;
import com.solvd.repairService.service.*;
import com.solvd.repairService.views.AdminView;
import com.solvd.repairService.views.OrdersView;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ModelService {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private final static Scanner scanner = new Scanner(System.in);

    public static ServiceCenters createServiceCenter(Long id, Logger LOGGER) {
        LOGGER.info("input name");
        var name = scanner.next();
        LOGGER.info("input description");
        var descr = scanner.next();
        LOGGER.info("input address");
        var address = scanner.next();

        return AdminService.createServiceCenter(id, name, descr, address);
    }

    public static CustomerProfiles createCustomerProfile(Long id, Logger LOGGER) {
        LOGGER.info("Nick");
        var nick = scanner.next();
        nick = scanner.next();
        LOGGER.info("Phone");
        var phone = scanner.next();

        return new CustomerProfiles(id, nick, phone);
    }

    public static Users createUser(Logger LOGGER) {
        LOGGER.info("login ");
        var login = scanner.next();
        LOGGER.info("password ");
        var password = scanner.next();

        return new Users(login, password);
    }

    public static Orders createOrder(Long id, Logger LOGGER) {
        Orders orders = new Orders(id);
        LOGGER.info("input userId");
        orders.userId(scanner.nextLong());
        LOGGER.info("input equipmentId");
        orders.equipmentId(scanner.nextLong());
        LOGGER.info("input executeId");
        orders.executeId(scanner.nextLong());

        return orders;
    }

    public static Orders createOrder(Logger LOGGER) {
        Orders orders = new Orders();
        LOGGER.info("input userId");
        orders.userId(scanner.nextLong());
        LOGGER.info("input equipmentId");
        orders.equipmentId(scanner.nextLong());
        LOGGER.info("input executeId");
        orders.executeId(scanner.nextLong());

        return orders;
    }

    public static EmployeePosts createEmployeePost(Logger LOGGER) {
        return createEmployeePost(0L, LOGGER);
    }

    public static EmployeePosts createEmployeePost(Long id, Logger LOGGER) {
        LOGGER.info("input role");
        var role = scanner.next();
        LOGGER.info("input description");
        var description = scanner.next();

        return new EmployeePosts(id, role, description);
    }

    public static EmployeeProfiles createEmployeeProfile(Long id, Logger LOGGER) {
        EmployeeProfiles profile = new EmployeeProfiles(id);
        LOGGER.info("input fullName");
        profile.fullName(scanner.next());
        profile.phone("+375(25)...");
        profile.experience(0);

        return profile;
    }

    public static Equipments createEquipment(Logger LOGGER) {
        Equipments equipment = new Equipments();
        try {
            LOGGER.debug("Type: ");
            equipment.type(scanner.next());
            LOGGER.debug("Producer: ");
            equipment.producer(scanner.next());
            LOGGER.debug("Model: ");
            equipment.model(scanner.next());
            LOGGER.debug("Price: ");
            equipment.price(Double.parseDouble(scanner.next()));

        } catch (Exception e) {
            createEquipment(LOGGER);
        }
        return equipment;
    }

    public static Equipments addEquipment(Logger LOGGER, CustomerProfiles profile) {
        var equipment = ModelService.createEquipment(LOGGER);
        try {
            equipment = OrdersView.equipmentsService().create(equipment);
        } catch (Exception e) {
            LOGGER.error(e);
            OrdersView.ordersActions(profile);
        }
        setProblemToDevice(equipment, addProblem());
        return equipment;
    }

    public static Users addUser(Users user, Logger LOGGER) {
        try {
            user = AdminView.usersService().create(user);
        } catch (Exception e) {
            LOGGER.error(e);
            addUser(user, LOGGER);
        }
        return user;
    }

    public static EmployeeProfiles getEmployeeFromUnoccupiedServiceCenter() {
        var center = OrdersView.serviceCentersService().findUnoccupied();
        return OrdersView.employeeProfileService().findByServiceCenter(center);
    }

    public static OrderExecutions addOrderExecution(Equipments equipment) {

        var employee = ModelService.getEmployeeFromUnoccupiedServiceCenter();
        return OrdersView.orderExecutionsService().create(equipment, employee, employee.center());
    }

    public static Problems addProblem() {
        return OrdersView.problemService().create();
    }

    public static void setProblemToDevice(Equipments equipment, Problems problem) {
        equipment.addProblem(addProblem());
        connectProblemAndEquipment(equipment, problem);
    }

    public static void connectProblemAndEquipment(Equipments equipment, Problems problem) {
        OrdersView.equipmentProblemService().create(equipment, problem);
    }

    public static Orders addOrder(CustomerProfiles profile, Equipments equipment, OrderExecutions orderExecution) {
        return OrdersView.ordersService().create(profile, equipment, orderExecution);
    }

    public static EmployeeProfiles addEmoloyee(EmployeeProfiles profileTmp) {
        return AdminView.employeeProfileService().create(profileTmp);
    }
}
