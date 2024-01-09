package com.solvd.repairService.views;

import com.solvd.repairService.DAO.JDBC.*;
import com.solvd.repairService.DAO.myBatisXML.*;
import com.solvd.repairService.helpers.Global;
import com.solvd.repairService.model.CustomerProfiles;
import com.solvd.repairService.model.Equipments;
import com.solvd.repairService.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Scanner;

import static com.solvd.repairService.views.CustomerProfileView.profileUI;

public class OrdersView {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private final static Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(CustomerProfileView.class);
    private static final OrdersService serviceO = Global.state()
            ? new OrdersService(new OrdersDAO())
            : new OrdersService(new OrdersBatisDAO());

    private static final EquipmentsService serviceE = Global.state()
            ? new EquipmentsService(new EquipmentsDAO())
            : new EquipmentsService(new EquipmentsBatisDAO());

    private static final ServiceCentersService serviceSC = Global.state()
            ? new ServiceCentersService(new ServiceCentersDAO())
            : new ServiceCentersService(new ServiceCentersBatisDAO());
    private static final OrderExecutionsService serviceOE = Global.state()
            ? new OrderExecutionsService(new OrderExecutionsDAO())
            : new OrderExecutionsService(new OrderExecutionsBatisDAO());
    private static final EmployerProfileService serviceEP = Global.state()
            ? new EmployerProfileService(new EmployeeProfilesDAO())
            : new EmployerProfileService(new EmployeeProfilesBatisDAO());

    private static final ProblemService serviceP = Global.state()
            ? new ProblemService(new ProblemsDAO())
            : new ProblemService(new ProblemsBatisDAO());

    private static final EquipmentProblemService serviceEqPr = Global.state()
            ? new EquipmentProblemService(new EquipmentProblemDAO())
            : new EquipmentProblemService(new EquipmentProblemBatisDAO());

    public static void ordersActions(CustomerProfiles profile) {
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
                break;
        }
    }

    public static void createNewOrder(CustomerProfiles profile) {
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

        serviceEqPr.create(equipment, problem);

        var orderExecution = serviceOE.create(equipment, employee, center);

        serviceO.create(profile, equipment, orderExecution);
        LOGGER.info("Order was successfully created");
        ordersActions(profile);

    }

    private static void checkOrderHistory(CustomerProfiles profile) {
        var orders = serviceO.ordersHistory(profile);
        HashSet<Long> hashSetId = new HashSet<>();
        if (orders != null) {
            LOGGER.info(orders.size() + " of Orders");
            for (var element : orders) {
                hashSetId.add(element.id());
                LOGGER.info(element);
            }
            System.out.println();
            LOGGER.debug("Input number ");
            String answer = scanner.nextLine();
            try {
                var a = Long.parseLong(answer);
                if (hashSetId.contains(a)) {
                    checkOrder(Long.parseLong(answer), profile);
                }
                else {
                    LOGGER.debug("[redirect...]");
                    checkOrderHistory(profile);
                }
            } catch (Exception e) {
                ordersActions(profile);
            }

        } else {
            LOGGER.info("History is empty");
            ordersActions(profile);
        }
    }

    private static void checkOrder(Long id, CustomerProfiles profile) {
        var order = serviceO.selectById(id);
        LOGGER.info("type: " + order.equipment().type());
        LOGGER.info("device: " + order.equipment().producer() + " " + order.equipment().model());
        LOGGER.info("need to " + order.equipment().getProblem().description());

        checkOrderHistory(profile);
    }
}
