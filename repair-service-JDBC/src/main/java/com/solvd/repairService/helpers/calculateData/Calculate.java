package com.solvd.repairService.helpers.calculateData;

import com.solvd.repairService.model.EmployerProfiles;
import com.solvd.repairService.model.Equipments;

import java.util.Random;

public class Calculate {
    public static double orderCost(Equipments equipment, EmployerProfiles profile) {
        return equipment.price() * ((1 + profile.experience() * 2) / 100)
                + 25 * equipment.getProblem().id();
    }

    public static int workDayCount(EmployerProfiles profile) {
        var v = new Random().nextInt(8) + (10f / profile.experience());
        return ((int) v);
    }


}
