package com.solvd.repairService.views;

import com.solvd.repairService.DAO.JDBC.UsersDAO;
import com.solvd.repairService.DAO.myBatisXML.UserBatisDAO;
import com.solvd.repairService.helpers.Global;
import com.solvd.repairService.model.Users;
import com.solvd.repairService.helpers.ModelService;
import com.solvd.repairService.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ValidationView {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private final static Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(ValidationView.class);
    private static final UsersService service = Global.console()
            ? new UsersService(new UsersDAO())
            : new UsersService(new UserBatisDAO());

    public static void loadValidationView() {
        CustomerProfileView.profileUI(new Users(6L, "log2", "pas2", false));
        LOGGER.info("Log in or registration?");
        LOGGER.info("1: log in");
        LOGGER.info("2: registration");
        LOGGER.info("0: Quit");
        var answer = scanner.next();
        switch (answer) {
            case "1":
                loginUser();
                break;
            case "2":
                registrationUser();
                break;
            case "3":
                System.exit(200);
                break;
            default:
                loadValidationView();
        }
    }

    private static void registrationUser() {
        try {
            Users user = service.create(ModelService.createUser(LOGGER));
            CustomerProfileView.profileUI(user);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            loadValidationView();
        }
    }

    private static void loginUser() {
        Users user;
        try {
            //user = service.findUserByLogin(receiveUserData().login());
            user = new Users(2L, "login@repairman.com", "1234", false);
            if (user.role())
                CustomerProfileView.profileUI(user);
            else
                AdminView.adminUI();
        } catch (Exception e) {
            LOGGER.info("User isn't exist");
            loadValidationView();
        }
    }
}
