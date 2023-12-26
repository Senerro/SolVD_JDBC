package com.solvd.repairService.views;

import com.solvd.repairService.DAO.UsersDAO;
import com.solvd.repairService.model.CustomerProfiles;
import com.solvd.repairService.model.Users;
import com.solvd.repairService.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class UserSettingsView {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }
    private final static Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(CustomerProfileView.class);
    public static CustomerProfiles settingsUI(CustomerProfiles profile) {
        LOGGER.debug("1: change login");
        LOGGER.debug("2: change password");
        String answer = scanner.nextLine();
        switch (answer) {
            case "1": changeLogin(profile); break;
            case "2": changePassword(profile); break;
            default: CustomerProfileView.profileUI(profile.user());
        }
        return profile;
    }

    private static void changePassword(CustomerProfiles profile) {
        LOGGER.info("Enter current password");
        String oldPass = scanner.nextLine();
        UsersService service = new UsersService(new UsersDAO());
        boolean isDetected = service.acceptPassword(oldPass, profile.id());
        if(!isDetected)
        {
            LOGGER.error("wrong password");
            changePassword(profile);
        }
        LOGGER.info("Enter new password");
        String newPass = scanner.nextLine();
        Users newUser = new Users(profile.user());
        newUser.password(newPass);
        try {
           var updatedUser = service.updateUser(profile.user(), newUser);
           profile.user(updatedUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        settingsUI(profile);
    }

    private static void changeLogin(CustomerProfiles profile) {
        LOGGER.info("Enter new login");
        String newLogin = scanner.nextLine();
        UsersService service = new UsersService(new UsersDAO());
        try {
            service.findUserByLogin(newLogin);
            LOGGER.error("User with the same login already exist");
        } catch (Exception e) {
            Users newUser = new Users(profile.user());
            newUser.login(newLogin);
            try {
                var updatedUser = service.updateUser(profile.user(), newUser);
                profile.user(updatedUser);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        settingsUI(profile);
    }
}
