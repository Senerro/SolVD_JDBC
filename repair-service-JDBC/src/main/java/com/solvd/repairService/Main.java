package com.solvd.repairService;

import com.solvd.repairService.helpers.calculateData.Global;
import com.solvd.repairService.views.ValidationView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
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
        LOGGER.debug("2: MyBatis");
        String answer = scanner.nextLine();
        switch (answer) {
            case "1":
                Global.state(false);
                break;
            case "2":
                Global.state(true);
                break;
            default: main(new String[1]);
        }
        ValidationView.loadValidationView();
    }
}