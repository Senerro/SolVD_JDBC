package com.solvd.repairService.views;

import com.solvd.repairService.DAO.CustomerProfilesDAO;
import com.solvd.repairService.model.CustomerProfiles;
import com.solvd.repairService.model.Users;
import com.solvd.repairService.service.CustomerProfilesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class CustomerProfileView {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }
    private final static Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(CustomerProfileView.class);

    public static void profileUI(Users user) {

        CustomerProfiles profile = null;
        var service = new CustomerProfilesService(new CustomerProfilesDAO());
        var isDetected = service.checkAvailability(new CustomerProfiles(user.id()));
        profile = isDetected ? service.selectById(user.id())
                : service.create(new CustomerProfiles(user.id()));
        profile.user(user);
        LOGGER.info("nickname: " + profile.nick());
        LOGGER.info("phone: " + profile.phone());
        LOGGER.info("email: " + user.login());

        System.out.print("\n");
        LOGGER.info("1: profile");
        LOGGER.info("2: orders");
        String answer = scanner.nextLine();
        switch (answer) {
            case "1":
                profileActions(profile);
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
        CustomerProfilesService service = new CustomerProfilesService(new CustomerProfilesDAO());
        try {
            profile.phone(service.updateProfile(profile, newProfile).phone());

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
            profile.nick( (service.updateProfile(profile, newProfile)).nick());
        } catch (Exception e) {
            LOGGER.error("same nick");
        }
        profileActions(profile);
    }

}
