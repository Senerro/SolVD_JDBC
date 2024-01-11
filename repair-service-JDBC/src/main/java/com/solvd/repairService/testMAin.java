package com.solvd.repairService;
import com.solvd.repairService.helpers.parsers.Jackson;
import com.solvd.repairService.model.*;
import com.solvd.repairService.views.AdminView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class testMAin {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(testMAin.class);

    public static void main(String[] args) {

        {
            var customer = Jackson.get(new CustomerProfiles());
            LOGGER.info(customer);
        }
        {
            var worker = Jackson.get(new EmployeeProfiles());
            LOGGER.debug(worker);
        }
        {
            var position = Jackson.get(new EmployeePosts());
            LOGGER.debug(position);
        }
        {
            var serviceCenter = Jackson.get(new ServiceCenters());
            LOGGER.debug(serviceCenter);
        }
        {
            var order = Jackson.get(new Orders());
            LOGGER.debug(order);
        }
    }
}
