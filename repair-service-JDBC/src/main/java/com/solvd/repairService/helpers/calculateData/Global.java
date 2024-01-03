package com.solvd.repairService.helpers.calculateData;

public class Global {
    private static boolean isJDBC = true;

    public static boolean state() {
        return isJDBC;
    }

    public static void state(boolean b) {
        isJDBC = b;
    }
}
