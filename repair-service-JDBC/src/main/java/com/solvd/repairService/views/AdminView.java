package com.solvd.repairService.views;

import com.solvd.repairService.DAO.JDBC.*;
import com.solvd.repairService.DAO.myBatisXML.*;
import com.solvd.repairService.helpers.Global;
import com.solvd.repairService.model.*;
import com.solvd.repairService.service.*;
import com.solvd.repairService.service.interfaces.IService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

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
    private static final EmployerProfileService serviceEP = Global.jdbc()
            ? new EmployerProfileService(new EmployeeProfilesDAO())
            : new EmployerProfileService(new EmployeeProfilesBatisDAO());

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
    private static ArrayList<CustomerProfiles> customerList;
    private static ArrayList<EmployeeProfiles> employeeList;
    private static ArrayList<EmployeePosts> postList;
    private static ArrayList<ServiceCenters> centerList;
    private static ArrayList<Orders> ordersList;

    public static void adminUI() {
        LOGGER.info("1. Customers");
        LOGGER.info("2. Workers");
        LOGGER.info("3. Worker positions");
        LOGGER.info("4. Orders");
        LOGGER.info("5. Services");
        var result = scanner.next();
        switch (result) {
            case "1":
                getCustomers();
                break;
            case "2":
                getWorkers();
                break;
            case "3":
                getPosts();
                break;
            case "4":
                getOrders();
                break;
            case "5":
                getServices();
                break;
            default:
                adminUI();
                break;
        }
        adminUI();
    }


    private static void postActions(ArrayList<EmployeePosts> list) {
        switch (actions()) {
            case "1":
                LOGGER.info(getModel(new ArrayList<>(list), servicePost));
                break;
            case "2":
                addPost(list);
                break;
            case "3":
                updatePost(list);
                break;
            case "4":
                deletePost(list);
                break;
            default:
                adminUI();
                break;
        }
    }

    private static void getOrders() {
        ArrayList<Orders> list = new ArrayList<>();
        try {
            list = serviceO.get();
            for (var element : list)
                LOGGER.info(element);
            ordersList = list;
        } catch (Exception e) {
            LOGGER.info(e);
        }
        orderActions(list);
    }

    private static void orderActions(ArrayList<Orders> list) {
        switch (actions()) {
            case "1":
                LOGGER.info(getModel(new ArrayList<>(list), serviceO));
                //getOrder(list);
                break;
            case "2":
                addOrder(list);
                break;
            case "3":
                updateOrder(list);
                break;
            case "4":
                deleteOrder(list);
                break;
            default:
                adminUI();
                break;
        }
    }

    private static void updateOrder(ArrayList<Orders> list) {
//        var order = getOrder(list);
        var order = (Orders) getModel(new ArrayList<>(list), serviceO);

        var newOrder = createOrder(order.id());
        try {
            serviceO.update(order, newOrder);
        } catch (Exception e) {
            LOGGER.error(e);
            updateOrder(list);
        }
        for (var element : list) {
            if (element.id().equals(newOrder.id())) {
                LOGGER.debug("old version: " + element + "\n");
                element = newOrder;
                LOGGER.debug("new version: " + element + "\n");
            }
        }
        orderActions(list);
    }

    private static Orders createOrder(Long id) {
        Orders orders = new Orders(id);
        LOGGER.info("input userId");
        orders.userId(scanner.nextLong());
        LOGGER.info("input equipmentId");
        orders.equipmentId(scanner.nextLong());
        LOGGER.info("input executeId");
        orders.executeId(scanner.nextLong());

        return orders;
    }

    private static void addOrder(ArrayList<Orders> list) {
        if (!Global.console()) {
            try {
                var dto = (Orders) AdminService.getPreparedModel(new Orders());

                var serviceCenter = serviceSC.get(dto.center().id());
                if (serviceCenter.id() <= 0) {
                    serviceCenter = serviceSC.create(dto.center());
                    dto.employee().serviceCenters(serviceCenter);
                }
                var customer = dto.customer();
                try {
                    Users user = serviceU.findUserByLogin(customer.user().login());
                    var tmp = serviceCP.get(user.id());
                    if (tmp == null) {
                        LOGGER.warn("profile for user wasn't created");
                        customer.user(user);
                        customer = serviceCP.create(customer);
                        LOGGER.warn("profile was created");
                    }
                    customer.user(user);
                } catch (Exception e) {
                    Users user = serviceU.create(customer.user());
                    customer.user(user);
                    serviceCP.create(customer);
                }
                var post = dto.employee().post();

                var employee = dto.employee();
                try {
                    Users user = serviceU.findUserByLogin(employee.user().login());
                    var tmp = serviceEP.get(user.id());
                    if (tmp == null) {
                        LOGGER.warn("profile for employee wasn't created");
                        employee.user(user);
                        employee.center(serviceCenter);
                        employee.post(post);
                        employee = serviceEP.create(employee);
                        LOGGER.warn("profile was created");
                    }
                    employee.user(user);
                } catch (Exception e) {

                    Users user = serviceU.create(employee.user());
                    employee.user(user);
                    employee.center(serviceCenter);
                    employee.post(post);
                    serviceEP.create(employee);
                }

                var problem = serviceP.create();
                dto.equipment().addProblem(problem);
                var equipment = serviceE.create(dto.equipment());


                var orderExecution = serviceOE.create(equipment, employee, serviceCenter);
                serviceO.create(customer, equipment, orderExecution);

            } catch (Exception e) {
                LOGGER.error("Some error with creating order");
                LOGGER.error(e.getMessage());
                LOGGER.error(e.getStackTrace());

                orderActions(list);
            }
        } else {
            ArrayList<CustomerProfiles> customerProfiles = new ArrayList<>();
            HashSet<Long> set = new HashSet<>();
            try {
                customerProfiles = serviceCP.get();
                for (var element : customerProfiles) {
                    LOGGER.info(element);
                    set.add(element.id());
                }
            } catch (Exception e) {
                LOGGER.info(e);
            }
            LOGGER.info("Input id");
            var id = scanner.nextLong();
            if (set.contains(id)) {
                OrdersView.createNewOrder(serviceCP.get(id));
                orderActions(list);
            } else addOrder(list);

            LOGGER.info("we can not set customer when any customer doesn't exist");
        }
        orderActions(list);
    }


    private static void deleteOrder(ArrayList<Orders> list) {
        //var order = getOrder(list);
        var order = (Orders) getModel(new ArrayList<>(list), serviceO);
        serviceO.delete(order);
        list.remove(order);
        orderActions(list);
    }

   /* private static Orders getOrder(ArrayList<Orders> list) {
        Orders order = null;
        if (!list.isEmpty()) {
            HashSet<Long> set = new HashSet<>();
            for (var element : list)
                set.add(element.id());

            LOGGER.info("Input id");
            var id = scanner.nextLong();
            if (set.contains(id)) {
                order = serviceO.get(id);
                LOGGER.info(order);
                return order;
            } else getOrder(list);
        }
        return order;
    }*/

    private static void getServices() {
        ArrayList<ServiceCenters> list = new ArrayList<>();
        try {
            list = serviceSC.get();
            for (var element : list)
                LOGGER.info(element);
            centerList = list;
        } catch (Exception e) {
            LOGGER.info(e);
        }
        serviceActions(list);
    }

    private static void serviceActions(ArrayList<ServiceCenters> list) {
        switch (actions()) {
            case "1":
               LOGGER.info(getModel(new ArrayList<>(list), serviceSC));
                break;
            case "2":
                addServiceCenter(list);
                break;
            case "3":
                updateService(list);
                break;
            case "4":
                deleteService(list);
                break;
            default:
                adminUI();
                break;
        }
    }

    private static void deleteService(ArrayList<ServiceCenters> list) {
//      var service = getServiceCenter(list);
        var service = (ServiceCenters) getModel(new ArrayList<>(list), serviceSC);
        serviceSC.delete(service);
        serviceActions(list);
    }

    private static void updateService(ArrayList<ServiceCenters> list) {
        //      var center = getServiceCenter(list);
        var center = (ServiceCenters) getModel(new ArrayList<>(list), serviceSC);
        var newCenter = createServiceCenter(center.id());
        try {
            serviceSC.update(center, newCenter);
        } catch (Exception e) {
            LOGGER.error(e);
            updateService(list);
        }
        for (var element : list) {
            if (element.id().equals(newCenter.id())) {
                LOGGER.debug("old version: " + element + "\n");
                element = newCenter;
                LOGGER.debug("new version: " + element + "\n");
            }
        }
        serviceActions(list);
    }

    public static ServiceCenters createServiceCenter(Long id) {
        LOGGER.info("input name");
        var name = scanner.next();
        LOGGER.info("input description");
        var descr = scanner.next();
        LOGGER.info("input address");
        var address = scanner.next();

        return AdminService.createServiceCenter(id, name, descr, address);
    }

    private static void addServiceCenter(ArrayList<ServiceCenters> list) {
        if (!Global.console()) {
            var center = (ServiceCenters) AdminService.getPreparedModel(new ServiceCenters());
            serviceSC.create(center);
            list.add(center);
        } else {
            ServiceCenters model = createServiceCenter(0L);
            try {
                var center = serviceSC.create(model);
                LOGGER.info(center);
                list.add(center);
            } catch (Exception e) {
                LOGGER.error(e);
                addServiceCenter(list);
            }
        }
        serviceActions(list);
    }

    private static ServiceCenters getServiceCenter(ArrayList<ServiceCenters> list) {
        if (!list.isEmpty()) {
            HashSet<Long> set = new HashSet<>();
            for (var element : list)
                set.add(element.id());

            LOGGER.info("Input id");
            var id = scanner.nextLong();
            if (set.contains(id)) {
                var center = serviceSC.get(id);
                LOGGER.info(center);
                return center;
            } else getServiceCenter(list);
        }
        LOGGER.info("You can not get customer when any customer doesn't exist");
        serviceActions(list);
        return null;
    }

    private static void getPosts() {
        ArrayList<EmployeePosts> list = new ArrayList<>();
        try {
            list = servicePost.get();
            for (var element : list)
                LOGGER.info(element);
            postList = list;
        } catch (Exception e) {
            LOGGER.info(e);
        }
        postActions(list);
    }


    private static void deletePost(ArrayList<EmployeePosts> list) {
        var post = (EmployeePosts) getModel(new ArrayList<>(list), servicePost);
        servicePost.delete(post);
        list.remove(post);
        postActions(list);

    }

    private static void updatePost(ArrayList<EmployeePosts> list) {
        var post = (EmployeePosts) getModel(new ArrayList<>(list), servicePost);
        var newPost = createEmployerPost(post.id());
        try {
            servicePost.update(post, newPost);
        } catch (Exception e) {
            LOGGER.error(e);
            updatePost(list);
        }
        for (var element : list) {
            if (element.id().equals(newPost.id())) {
                LOGGER.debug("old version: " + element + "\n");
                element = newPost;
                LOGGER.debug("new version: " + element + "\n");
            }
        }
        postActions(list);
    }

    private static EmployeePosts createEmployerPost(Long id) {
        LOGGER.info("input role");
        var role = scanner.next();
        LOGGER.info("input description");
        var description = scanner.next();
        return new EmployeePosts(id, role, description);

    }

    private static void addPost(ArrayList<EmployeePosts> list) {
        if (!Global.console()) {
            try {
                var post = (EmployeePosts) AdminService.getPreparedModel(new EmployeePosts());
                post = servicePost.create(post);
                LOGGER.info(post);
                LOGGER.info("created");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            LOGGER.info("role ");
            var role = scanner.next();
            LOGGER.info("description ");
            var description = scanner.next();

            EmployeePosts model = new EmployeePosts(role, description);
            try {
                var post = servicePost.create(model);
                LOGGER.info(post);
                list.add(post);
            } catch (Exception e) {
                LOGGER.error(e);
                addPost(list);
            }
        }
        postActions(list);
    }

    private static void getWorkers() {
        ArrayList<EmployeeProfiles> list;
        try {
            list = serviceEP.get();
            for (var element : list)
                LOGGER.info(element);
            employeeList = list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        workersActions(list);
    }

    private static void workersActions(ArrayList<EmployeeProfiles> list) {
        switch (actions()) {
            case "1":
                LOGGER.info(getModel(new ArrayList<>(list), serviceEP));
                break;
            case "2":
                addWorker(list);
                break;
            case "3":
                updateWorker(list);
                break;
            case "4":
                deleteWorker(list);
                break;
            default:
                adminUI();
                break;
        }
    }

    private static void deleteWorker(ArrayList<EmployeeProfiles> list) {
        var profile = (EmployeeProfiles) getModel(new ArrayList<>(list), serviceEP);
        serviceEP.delete(profile);
        list.remove(profile);
        workersActions(list);
    }

    private static void updateWorker(ArrayList<EmployeeProfiles> list) {
        var worker = (EmployeeProfiles) getModel(new ArrayList<>(list), serviceEP);
        var newWorker = createWorker(worker.id());
        try {
            serviceEP.update(worker, newWorker);
        } catch (Exception e) {
            LOGGER.error(e);
            updateWorker(list);
        }
        for (var element : list) {
            if (element.id().equals(newWorker.id())) {
                LOGGER.debug("old version: " + element + "\n");
                element = newWorker;
                LOGGER.debug("new version: " + element + "\n");
            }
        }
        workersActions(list);
    }

    private static EmployeeProfiles createWorker(Long id) {

        EmployeeProfiles profile = new EmployeeProfiles(id);
        LOGGER.info("input fullName");
        profile.fullName(scanner.next());
        LOGGER.info("input phone");
        profile.phone(scanner.next());
        LOGGER.info("input exp");
        profile.experience(Double.parseDouble(scanner.next()));
        return profile;
    }

    private static void addWorker(ArrayList<EmployeeProfiles> list) {
        if (!Global.console()) {
            try {
                var profile = (EmployeeProfiles) AdminService.getPreparedModel(new EmployeeProfiles());
                var post = servicePost.get(profile.post().id());
                if (post == null) {
                    LOGGER.error("position " + post + "doesn't exist");
                    post = servicePost.create(profile.post());
                    LOGGER.debug("position " + post + "was created");
                }
                profile.post(post);
                Users user = new Users();
                try {
                    user = serviceU.findUserByLogin(profile.user().login());
                } catch (Exception e) {
                    user = serviceU.create(profile.user());
                }
                var center = serviceSC.get(profile.center().id());
                if (center.id() <= 0)
                    center = serviceSC.create(profile.center());

                var tmp = serviceEP.get(user.id());
                if (tmp != null)
                    LOGGER.error("profile for employee " + profile.fullName() + " already exist");
                else {
                    profile.id(user.id());
                    profile.center(center);
                    var result = serviceEP.create(profile);
                    if (result.id() > 0) {
                        LOGGER.info("profile was successfully created");
                        list.add(result);
                    } else LOGGER.error("profile wasn't created");
                }
                workersActions(list);

            } catch (Exception e) {
                LOGGER.warn("profile wasn't created");
                LOGGER.error(e.getMessage());
                workersActions(list);
            }
        } else {
            LOGGER.info("login ");
            var login = scanner.next();
            LOGGER.info("password ");
            var password = scanner.next();

            Users user = new Users(login, password);
            try {
                user = serviceU.create(user);
            } catch (Exception e) {
                LOGGER.error(e);
                addWorker(list);
            }
            EmployeeProfiles profileTmp = new EmployeeProfiles(user.id());
            LOGGER.info("input fullName");
            profileTmp.fullName(scanner.next());
            profileTmp.phone("+375(25)...");
            profileTmp.experience(0);

            ArrayList<EmployeePosts> posts;
            HashSet<Long> set = new HashSet<>();
            posts = servicePost.get();
            for (var element : posts) {
                set.add(element.id());
                LOGGER.info(element);
            }
            boolean flag1 = false;
            do {
                LOGGER.info("input post");
                var id = scanner.nextLong();
                if (set.contains(id)) {
                    profileTmp.postId(id);
                    flag1 = true;
                }
            } while (!flag1);

            ArrayList<ServiceCenters> centers = new ArrayList<>();
            HashSet<Long> ids = new HashSet<>();
            try {
                centers = serviceSC.get();
            } catch (Exception e) {
                LOGGER.info(e);
            }
            for (var element : centers) {
                ids.add(element.id());
                LOGGER.info(element);
            }
            boolean flag = false;
            do {
                LOGGER.info("input service center");
                var id = scanner.nextLong();
                if (ids.contains(id)) {
                    profileTmp.serviceCenterId(id);
                    flag = true;
                }
            } while (!flag);

            var profile = serviceEP.create(profileTmp);
            LOGGER.info(profile);
            list.add(profile);
        }
        workersActions(list);
    }

    private static void getCustomers() {
        ArrayList<CustomerProfiles> list = new ArrayList<>();
        try {
            list = serviceCP.get();
            for (var element : list)
                LOGGER.info(element);
            customerList = list;
        } catch (Exception e) {
            LOGGER.info(e);
        }
        customerActions(list);
    }

    private static void customerActions(ArrayList<CustomerProfiles> list) {
        switch (actions()) {
            case "1":
                LOGGER.info(getModel(new ArrayList<>(list), serviceCP));
                break;
            case "2":
                addCustomer(list);
                break;
            case "3":
                updateCustomer(list);
                break;
            case "4":
                deleteCustomer(list);
                break;
            default:
                adminUI();
                break;
        }
        customerActions(list);
    }

    private static void deleteCustomer(ArrayList<CustomerProfiles> list) {
        var profile = (CustomerProfiles) getModel(new ArrayList<>(list), serviceCP);
        serviceCP.delete(profile);
        list.remove(profile);
        customerActions(list);
    }

    private static void updateCustomer(ArrayList<CustomerProfiles> list) {
        var profile = (CustomerProfiles) getModel(new ArrayList<>(list), serviceCP);
        var newProfile = createCustomerProfile(profile.id());
        try {
            serviceCP.updateProfile(profile, newProfile);
        } catch (Exception e) {
            LOGGER.error(e);
            updateCustomer(list);
        }
        for (var element : list) {
            if (element.id().equals(newProfile.id())) {
                LOGGER.debug("old version: " + element + "\n");
                element = newProfile;
                LOGGER.debug("new version: " + element + "\n");
            }
        }
        customerActions(list);
    }

    private static void addCustomer(ArrayList<CustomerProfiles> profiles) {
        if (!Global.console()) {
            try {
                var profile = (CustomerProfiles) AdminService.getPreparedModel(new CustomerProfiles());
                var user = serviceU.create(profile.user());
                profile.user().id(user.id());
                profile.id(user.id());
                serviceCP.create(profile);
            } catch (Exception e) {
                customerActions(profiles);
            }

        } else {
            LOGGER.info("login ");
            var login = scanner.next();
            LOGGER.info("password ");
            var password = scanner.next();

            Users user = new Users(login, password);
            try {
                user = serviceU.create(user);
            } catch (Exception e) {
                LOGGER.error(e);
                addCustomer(profiles);
            }
            var profile = serviceCP.create(createCustomerProfile(user.id()));

            LOGGER.info(profile);
            profiles.add(profile);
            LOGGER.debug("User + " + profile + "was successfully added");
        }
        customerActions(profiles);

    }

    private static CustomerProfiles createCustomerProfile(Long id) {
        LOGGER.info("Nick");
        var nick = scanner.next();
        nick = scanner.next();
        LOGGER.info("Phone");
        var phone = scanner.next();

        return new CustomerProfiles(id, nick, phone);
    }

    private static AbstractModel getModel(ArrayList<AbstractModel> list, IService service) {
        var set = AdminService.getIdFromList(new ArrayList<>(list));
        if(set != null) {
            LOGGER.info("Input id");
            var id = scanner.nextLong();
            if (set.contains(id)) {
                return service.get(id);
            } else getModel(list, service);
        }
        return null;
    }

    private static String actions() {
        LOGGER.info("1: get");
        LOGGER.info("2: add");
        LOGGER.info("3: update");
        LOGGER.info("4: delete");
        return scanner.next();
    }
}
