package com.solvd.repairService.views;

import com.solvd.repairService.DAO.UsersDAO;
import com.solvd.repairService.model.Users;
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
    private static   UsersService service = 5>4 ? new UsersService(new UsersDAO())
                                                : new UsersService(new UsersDAO());

    public static void loadValidationView() {
        LOGGER.info("Log in or registration?");
        LOGGER.info("1: log in");
        LOGGER.info("2: registration");
        var answer = scanner.nextLine();
        switch (answer) {
            case "1":
                loginUser();
                break;
            case "2":
                registrationUser();
                break;
            default:
                loadValidationView();
        }
    }

    private static void registrationUser() {
        Users user = null;
        try {
            user = service.create(receiveUserData());
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            loadValidationView();
        }
        CustomerProfileView.profileUI(user);
    }

    private static void loginUser() {
        Users user = null;
        try {
            user = service.findUserByLogin(receiveUserData().login());
        } catch (Exception e) {
            LOGGER.info("User isn't exist");
            registrationUser();
        }
        CustomerProfileView.profileUI(user);
    }

    private static Users receiveUserData() {
        String login;
        String password;
        do {
            LOGGER.info("Input login");
            login = scanner.nextLine();
        }
        while (login.length() < 4);
        do {
            LOGGER.info("Input password");
            password = scanner.nextLine();
        }
        while (password.length() < 4);
        return new Users(login, password);
    }
}
