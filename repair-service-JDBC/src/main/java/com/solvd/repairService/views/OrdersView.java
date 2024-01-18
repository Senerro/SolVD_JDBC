package com.solvd.repairService.views;

import com.solvd.repairService.DAO.JDBC.*;
import com.solvd.repairService.DAO.myBatisXML.*;
import com.solvd.repairService.helpers.Global;
import com.solvd.repairService.helpers.ModelService;
import com.solvd.repairService.helpers.parsers.ArrayGenericConverter;
import com.solvd.repairService.model.CustomerProfiles;
import com.solvd.repairService.model.Orders;
import com.solvd.repairService.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

import static com.solvd.repairService.views.CustomerProfileView.profileUI;

public class OrdersView {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private final static Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(CustomerProfileView.class);
    private static final OrdersService serviceO = Global.console()
            ? new OrdersService(new OrdersDAO())
            : new OrdersService(new OrdersBatisDAO());

    private static final EquipmentsService serviceE = Global.console()
            ? new EquipmentsService(new EquipmentsDAO())
            : new EquipmentsService(new EquipmentsBatisDAO());

    private static final ServiceCentersService serviceSC = Global.console()
            ? new ServiceCentersService(new ServiceCentersDAO())
            : new ServiceCentersService(new ServiceCentersBatisDAO());
    private static final OrderExecutionsService serviceOE = Global.console()
            ? new OrderExecutionsService(new OrderExecutionsDAO())
            : new OrderExecutionsService(new OrderExecutionsBatisDAO());
    private static final EmployeeProfileService serviceEP = Global.console()
            ? new EmployeeProfileService(new EmployeeProfilesDAO())
            : new EmployeeProfileService(new EmployeeProfilesBatisDAO());

    private static final ProblemService serviceP = Global.console()
            ? new ProblemService(new ProblemsDAO())
            : new ProblemService(new ProblemsBatisDAO());

    private static final EquipmentProblemService serviceEqPr = Global.console()
            ? new EquipmentProblemService(new EquipmentProblemDAO())
            : new EquipmentProblemService(new EquipmentProblemBatisDAO());

    public static EmployeeProfileService employeeProfileService() {
        return serviceEP;
    }

    public static ServiceCentersService serviceCentersService() {
        return serviceSC;
    }

    public static ProblemService problemService() {
        return serviceP;
    }

    public static EquipmentsService equipmentsService() {
        return serviceE;
    }

    public static OrderExecutionsService orderExecutionsService() {
        return serviceOE;
    }

    public static OrdersService ordersService() {
        return serviceO;
    }

    public static EquipmentProblemService equipmentProblemService() {
        return serviceEqPr;
    }

    public static void ordersActions(CustomerProfiles profile) {
        LOGGER.debug("1: create new order");
        LOGGER.debug("2: check order history");
        LOGGER.debug("3: return");

        String answer = scanner.nextLine();
        switch (answer) {
            case "1":
                createNewOrder(profile);
                ordersActions(profile);
                break;
            case "2":
                checkOrderHistory(profile);
                ordersActions(profile);
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

        var equipment = ModelService.addEquipment(LOGGER, profile);
        var order = ModelService.addOrder(profile, equipment, ModelService.addOrderExecution(equipment));
        if (order.id() != 0)
            LOGGER.info("Order was successfully created");
    }

    private static void checkOrderHistory(CustomerProfiles profile) {

        ArrayList<Orders> orders = serviceO.ordersHistory(profile);
        for (var element : orders) {
            LOGGER.info(element);
        }
        LOGGER.info(AdminView.getModel(ArrayGenericConverter.reconvert(orders), serviceO));
    }
}
