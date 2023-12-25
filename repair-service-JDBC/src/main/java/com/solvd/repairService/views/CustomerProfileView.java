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
        CustomerProfilesService service = new CustomerProfilesService(new CustomerProfilesDAO());
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
                advansedSettingsUI(profile);
                break;
            default:
                profileUI(profile.user());
        }
    }

    private static void advansedSettingsUI(CustomerProfiles profile) {
    }

    private static void changePhone(CustomerProfiles profile) {

    }

    private static void changeNick(CustomerProfiles profile) {
        
    }

}
