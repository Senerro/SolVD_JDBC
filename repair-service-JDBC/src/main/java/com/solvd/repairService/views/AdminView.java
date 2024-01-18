package com.solvd.repairService.views;

import com.solvd.repairService.DAO.JDBC.*;
import com.solvd.repairService.DAO.myBatisXML.*;
import com.solvd.repairService.helpers.Global;
import com.solvd.repairService.helpers.ModelService;
import com.solvd.repairService.helpers.enums.ModelsContainer;
import com.solvd.repairService.helpers.parsers.ArrayGenericConverter;
import com.solvd.repairService.model.*;
import com.solvd.repairService.service.*;
import com.solvd.repairService.service.interfaces.IService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class AdminView {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private final static Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(AdminView.class);
    private static final CustomerProfilesService serviceCP = Global.jdbc()
            ? new CustomerProfilesService(new CustomerProfilesDAO())
            : new CustomerProfilesService(new CustomerProfilesBatisDAO());
    private static final UsersService serviceU = Global.jdbc()
            ? new UsersService(new UsersDAO())
            : new UsersService(new UserBatisDAO());

    private static final OrdersService serviceO = Global.jdbc()
            ? new OrdersService(new OrdersDAO())
            : new OrdersService(new OrdersBatisDAO());

    private static final ServiceCentersService serviceSC = Global.jdbc()
            ? new ServiceCentersService(new ServiceCentersDAO())
            : new ServiceCentersService(new ServiceCentersBatisDAO());
    private static final EmployeeProfileService serviceEP = Global.jdbc()
            ? new EmployeeProfileService(new EmployeeProfilesDAO())
            : new EmployeeProfileService(new EmployeeProfilesBatisDAO());

    private static final EmployerPostsService servicePost = Global.jdbc()
            ? new EmployerPostsService(new EmployeePostsDAO())
            : new EmployerPostsService(new EmployerPostsBatisDAO());

    private static final EquipmentsService serviceE = Global.jdbc()
            ? new EquipmentsService(new EquipmentsDAO())
            : new EquipmentsService(new EquipmentsBatisDAO());

    private static final OrderExecutionsService serviceOE = Global.jdbc()
            ? new OrderExecutionsService(new OrderExecutionsDAO())
            : new OrderExecutionsService(new OrderExecutionsBatisDAO());
    private static final ProblemService serviceP = Global.jdbc()
            ? new ProblemService(new ProblemsDAO())
            : new ProblemService(new ProblemsBatisDAO());

    public static UsersService usersService() {
        return serviceU;
    }

    public static CustomerProfilesService customerProfilesService() {
        return serviceCP;
    }

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

    public static EmployerPostsService employeePostService() {
        return servicePost;
    }

    public static void adminUI() {
        LOGGER.info("1. Customers");
        LOGGER.info("2. Workers");
        LOGGER.info("3. Worker positions");
        LOGGER.info("4. Orders");
        LOGGER.info("5. Services");
        LOGGER.info("0. logout");
        var result = scanner.next();
        switch (result) {
            case "1":
                modelActions(getModels(serviceCP), serviceCP, ModelsContainer.CUSTOMER);
                break;
            case "2":
                modelActions(getModels(serviceEP), serviceEP, ModelsContainer.EMPLOYEE_PROFILE);
                break;
            case "3":
                modelActions(getModels(servicePost), servicePost, ModelsContainer.EMPLOYEE_POST);
                break;
            case "4":
                modelActions(getModels(serviceO), serviceO, ModelsContainer.ORDER);
                break;
            case "5":
                modelActions(getModels(serviceSC), serviceSC, ModelsContainer.SERVICE_CENTER);
                break;
            case "0":
                ValidationView.loadValidationView();
            default:
                adminUI();
                break;
        }
        adminUI();
    }

    private static void addOrder(ArrayList<Orders> list) {
        if (!Global.console()) {
            try {
                var dto = (Orders) AdminService.getPreparedModel(new Orders());
                var order = serviceO.create(AdminService.checkCustomerProfile(dto.customer()),
                        AdminService.checkEquipment(dto.equipment()),
                        AdminService.checkOrderExecution(dto));
                list.add(order);
            } catch (Exception e) {
                LOGGER.error("Some error with creating order");
                LOGGER.error(e.getMessage());
                modelActions(ArrayGenericConverter.reconvert(list), serviceO, ModelsContainer.ORDER);
            }
        } else {
            var profile = (CustomerProfiles) getModel(getModels(serviceCP), serviceCP);
            OrdersView.createNewOrder(serviceCP.get(profile.id()));
            LOGGER.info("we can not set customer when any customer doesn't exist");
        }
    }

    private static void addServiceCenter(ArrayList<ServiceCenters> list) {
        if (!Global.console()) {
            var center = (ServiceCenters) AdminService.getPreparedModel(new ServiceCenters());
            serviceSC.create(center);
            list.add(center);
        } else {
            ServiceCenters model = ModelService.createServiceCenter(0L, LOGGER);
            try {
                var center = serviceSC.create(model);
                LOGGER.info(center);
                list.add(center);
            } catch (Exception e) {
                LOGGER.error(e);
                addServiceCenter(list);
            }
        }
    }

    private static void addPost(ArrayList<EmployeePosts> list) {
        if (!Global.console()) {
            var post = (EmployeePosts) AdminService.getPreparedModel(new EmployeePosts());
            post = servicePost.create(post);
            list.add(post);
        } else {
            var model = ModelService.createEmployeePost(LOGGER);
            try {
                var post = servicePost.create(model);
                list.add(post);
                LOGGER.debug(post);
            } catch (Exception e) {
                LOGGER.error(e);
                addPost(list);
            }
        }
    }

    private static void addWorker(ArrayList<EmployeeProfiles> list) {
        if (!Global.console()) {
            var profile = (EmployeeProfiles) AdminService.getPreparedModel(new EmployeeProfiles());
            var worker = AdminService.checkEmployeeProfile(
                    profile,
                    AdminService.checkPost(profile.post()),
                    AdminService.checkServiceCenter(profile.center()));
            list.add(worker);
        } else {
            EmployeeProfiles profileTmp = ModelService.createEmployeeProfile(
                    ModelService.addUser(
                            ModelService.createUser(LOGGER), LOGGER).id(), LOGGER);

            setEmployeeAttributes(profileTmp);

            var profile = ModelService.addEmoloyee(profileTmp);
            list.add(profile);
            LOGGER.info(profile);
        }
    }

    private static void addCustomer(ArrayList<CustomerProfiles> profiles) {
        if (!Global.console()) {
            try {
                var profile = (CustomerProfiles) AdminService.getPreparedModel(new CustomerProfiles());
                AdminService.checkCustomerProfile(profile);
                profiles.add(profile);
            } catch (Exception e) {
                LOGGER.error("something happened in non-console addCustomer");
                modelActions(ArrayGenericConverter.reconvert(profiles), serviceCP, ModelsContainer.CUSTOMER);
            }
        } else {
            Users user = AdminService.checkUser(ModelService.createUser(LOGGER));
            var profile = serviceCP.create(ModelService.createCustomerProfile(user.id(), LOGGER));
            profiles.add(profile);
            LOGGER.debug("User + " + profile + "was successfully added");
        }
    }

    public static void setEmployeeAttributes(EmployeeProfiles profileTmp) {

        var post = (EmployeePosts) getModel(getModels(servicePost), servicePost);
        var center = (ServiceCenters) getModel(getModels(serviceSC), serviceSC);

        profileTmp.post(post);
        profileTmp.center(center);
    }

    private static void debugChangingDemonstration(ArrayList<AbstractModel> list, AbstractModel model) {
        for (var element : list) {
            if (element.id().equals(model.id())) {
                LOGGER.debug("old version: " + element + "\n");
                element = model;
                LOGGER.debug("new version: " + element + "\n");
            }
        }
    }

    public static AbstractModel getModel(ArrayList<AbstractModel> list, IService service) {
        var set = AdminService.getIdFromList(new ArrayList<>(list));
        assert set != null;
        LOGGER.info("Input id");
        var id = scanner.nextLong();
        if (!set.contains(id)) {
            getModel(list, service);
        }
        return service.get(id);
    }

    public static void modelActions(ArrayList<AbstractModel> list, IService service, ModelsContainer modelsContainer) {
        switch (actions()) {
            case "1":
                printModel(getModel(new ArrayList<>(list), service));
                break;
            case "2":
                addModel(list, modelsContainer);
                break;
            case "3":
                modelActions(updateModel(list, modelsContainer, service),
                        service, modelsContainer);
                break;
            case "4":
                modelActions(deleteModel(list, service),
                        service, modelsContainer);
                break;
            default:
                adminUI();
                break;
        }
        adminUI();
    }

    public static ArrayList<AbstractModel> getModels(IService service) {
        ArrayList<AbstractModel> list = new ArrayList<>();
        try {
            list = service.get();
            for (var element : list)
                LOGGER.info(element);

        } catch (Exception e) {
            LOGGER.info(e);
        }
        return list;
    }

    private static void printModel(AbstractModel model) {
        LOGGER.info(model);
    }

    private static void addModel(ArrayList<AbstractModel> list, ModelsContainer modelsContainer) {
        switch (modelsContainer) {
            case ORDER:
                addOrder(ArrayGenericConverter.convert(list));
                modelActions(ArrayGenericConverter.reconvert(list), serviceO, ModelsContainer.ORDER);
                break;
            case CUSTOMER:
                addCustomer(ArrayGenericConverter.convert(list));
                modelActions(ArrayGenericConverter.reconvert(list), serviceCP, ModelsContainer.CUSTOMER);
                break;
            case EMPLOYEE_POST:
                addPost(ArrayGenericConverter.convert(list));
                modelActions(ArrayGenericConverter.reconvert(list), servicePost, ModelsContainer.EMPLOYEE_POST);
                break;
            case EMPLOYEE_PROFILE:
                addWorker(ArrayGenericConverter.convert(list));
                modelActions(ArrayGenericConverter.reconvert(list), serviceEP, ModelsContainer.EMPLOYEE_PROFILE);
                break;
            case SERVICE_CENTER:
                addServiceCenter(ArrayGenericConverter.convert(list));
                modelActions(ArrayGenericConverter.reconvert(list), serviceSC, ModelsContainer.SERVICE_CENTER);
                break;
        }
    }

    private static ArrayList<AbstractModel> updateModel(ArrayList<AbstractModel> list, ModelsContainer mc, IService service) {
        var old = getModel(new ArrayList<>(list), service);
        var updatedModel = createModelEntity(old.id(), mc);
        try {
            service.update(old, updatedModel);
        } catch (Exception e) {
            LOGGER.error(e);
            updateModel(list, mc, service);
        }
        var reconvertedList = ArrayGenericConverter.reconvert(list);
        debugChangingDemonstration(reconvertedList, updatedModel);
        return list;
    }

    private static AbstractModel createModelEntity(Long id, ModelsContainer modelsContainer) {
        switch (modelsContainer) {
            case ORDER:
                return ModelService.createOrder(id, LOGGER);
            case CUSTOMER:
                return ModelService.createCustomerProfile(id, LOGGER);
            case EMPLOYEE_POST:
                return ModelService.createEmployeePost(id, LOGGER);
            case EMPLOYEE_PROFILE:
                return ModelService.createEmployeeProfile(id, LOGGER);
            case SERVICE_CENTER:
                return ModelService.createServiceCenter(id, LOGGER);
        }
        throw new RuntimeException("Unexpected model");
    }


    private static ArrayList<AbstractModel> deleteModel(ArrayList<AbstractModel> list, IService service) {
        var model = getModel(new ArrayList<>(list), service);
        service.delete(model);
        list.remove(model);

        return list;
    }

    private static String actions() {
        LOGGER.info("1: get");
        LOGGER.info("2: add");
        LOGGER.info("3: update");
        LOGGER.info("4: delete");

        return scanner.next();
    }
}