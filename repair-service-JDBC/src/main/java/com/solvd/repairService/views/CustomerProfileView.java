package com.solvd.repairService.views;

import com.solvd.repairService.DAO.JDBC.CustomerProfilesDAO;
import com.solvd.repairService.DAO.myBatisXML.CustomerProfilesBatisDAO;
import com.solvd.repairService.helpers.Global;
import com.solvd.repairService.model.*;
import com.solvd.repairService.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static com.solvd.repairService.views.OrdersView.ordersActions;

public class CustomerProfileView {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }
    private final static Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(CustomerProfileView.class);
    private static final CustomerProfilesService serviceCP = Global.console()
            ? new CustomerProfilesService(new CustomerProfilesDAO())
            : new CustomerProfilesService(new CustomerProfilesBatisDAO());
    public static void profileUI(Users user) {

        CustomerProfiles profile;

        var isDetected = serviceCP.checkAvailability(new CustomerProfiles(user.id()));
        profile = isDetected ? serviceCP.get(user.id())
                : serviceCP.create(new CustomerProfiles(user.id()));
        profile.user(user);
        LOGGER.info(profile);

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
