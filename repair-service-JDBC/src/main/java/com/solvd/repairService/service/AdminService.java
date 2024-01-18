package com.solvd.repairService.service;

import com.solvd.repairService.helpers.Global;
import com.solvd.repairService.helpers.ModelService;
import com.solvd.repairService.helpers.calculateData.Calculate;
import com.solvd.repairService.helpers.parsers.JAXB;
import com.solvd.repairService.helpers.parsers.Jackson;
import com.solvd.repairService.model.*;
import com.solvd.repairService.views.AdminView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.HashSet;

public class AdminService {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(AdminService.class);

    public static AbstractModel getPreparedModel(AbstractModel model) {
        if (model.clazz().equals(CustomerProfiles.class)) {
            if (Global.json())
                return Jackson.get(new CustomerProfiles());
            if (Global.jaxb())
                return JAXB.get(new CustomerProfiles());
        }
        if (model.clazz().equals(EmployeeProfiles.class)) {
            if (Global.json())
                return Jackson.get(new EmployeeProfiles());
            if (Global.jaxb())
                return JAXB.get(new EmployeeProfiles());
        }
        if (model.clazz().equals(EmployeePosts.class)) {
            if (Global.json())
                return Jackson.get(new EmployeePosts());
            if (Global.jaxb())
                return JAXB.get(new EmployeePosts());
        }
        if (model.clazz().equals(ServiceCenters.class)) {
            if (Global.json())
                return Jackson.get(new ServiceCenters());
            if (Global.jaxb())
                return JAXB.get(new ServiceCenters());
            if (Global.console())
                return ModelService.createServiceCenter(0L, LOGGER);
        }
        if (model.clazz().equals(Orders.class)) {
            if (Global.json())
                return Jackson.get(new Orders());
            if (Global.jaxb())
                return JAXB.get(new Orders());
        }
        throw new RuntimeException("Illegal class " + model.clazz() + " in getPreparedModel() method");
    }

    public static ServiceCenters createServiceCenter(Long id, String name, String descr, String address) {
        ServiceCenters center = new ServiceCenters(id);
        center.description(descr);
        center.name(name);
        center.address(address);
        return center;
    }

    public static HashSet<Long> getIdFromList(ArrayList<AbstractModel> list) {
        if (!list.isEmpty()) {
            HashSet<Long> set = new HashSet<>();
            for (var element : list)
                set.add(element.id());
            return set;
        } else return null;
    }

    public static ServiceCenters checkServiceCenter(ServiceCenters center) {
        var serviceCenter = AdminView.serviceCentersService().get(center.id());
        if (serviceCenter.id() == 0) {
            serviceCenter = AdminView.serviceCentersService().create(center);
        }
        return serviceCenter;
    }

    public static CustomerProfiles checkCustomerProfile(CustomerProfiles customer) {

        Users user = checkUser(customer.user());
        customer.user(user);
        var tmp = AdminView.customerProfilesService().get(user.id());
        if (tmp == null) {
            LOGGER.warn("profile for user wasn't created");
            customer.user(user);
            customer = AdminView.customerProfilesService().create(customer);
            LOGGER.warn("profile was created");
        } else {
            customer = tmp;
            customer.user(user);
        }
        return customer;
    }

    public static Users checkUser(Users user) {
        try {
            return AdminView.usersService().findUserByLogin(user.login());
        } catch (Exception e) {
            try {
                return AdminView.usersService().create(user);
            } catch (Exception ex) {
                throw new RuntimeException("It was try to create not spotted user, but he was spotted (lol, what?)");
            }
        }
    }

    public static EmployeeProfiles checkEmployeeProfile(EmployeeProfiles employee, EmployeePosts post, ServiceCenters center) {
        Users user = checkUser(employee.user());

        var tmp = AdminView.employeeProfileService().get(user.id());
        if (tmp == null) {
            LOGGER.warn("profile for employee wasn't created");
            employee.user(user);
            employee.center(center);
            employee.post(post);
            employee = AdminView.employeeProfileService().create(employee);
            LOGGER.warn("profile was created");
        } else {
            employee = tmp;
        }
        return employee;
    }

    public static Equipments generateProblemToEquipment(Equipments equipment) {
        var problem = AdminView.problemService().create();
        equipment.addProblem(problem);
        return equipment;
    }

    public static Equipments checkEquipment(Equipments equipment) {
        try {
            return AdminView.equipmentsService().create(generateProblemToEquipment(equipment));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static OrderExecutions checkOrderExecution(Orders order) {
        ServiceCenters center = checkServiceCenter(order.center());
        Equipments equipment = checkEquipment(order.equipment());
        EmployeeProfiles employee = checkEmployeeProfile(order.employee(), order.employee().post(), center);

        return AdminView.orderExecutionsService().create(Calculate.orderCost(equipment, employee), employee, center);
    }

    public static EmployeePosts checkPost(EmployeePosts post) {
        post = AdminView.employeePostService().get(post.id());
        if (post == null) {
            post = AdminView.employeePostService().create(post);
            LOGGER.debug("position " + post + "was created");
        }
        return post;
    }
}
