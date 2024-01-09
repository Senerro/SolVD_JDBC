package com.solvd.repairService;

import com.solvd.repairService.helpers.Global;
import com.solvd.repairService.views.ValidationView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private final static Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(ValidationView.class);

    public static void main(String[] args) {

        LOGGER.debug("JDBC or my batis?");
        LOGGER.debug("1: JDBC");
        LOGGER.debug("2: Stax");
        String answer = scanner.nextLine();
        switch (answer) {
            case "1":
                Global.jaxb(false);
                break;
            case "2":
                Global.jaxb(true);
                break;
            default: main(new String[1]);
        }
        ValidationView.loadValidationView();
    }
}