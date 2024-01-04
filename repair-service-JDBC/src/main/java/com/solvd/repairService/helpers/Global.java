package com.solvd.repairService.helpers;

public class Global {
    private static boolean isJDBC = true;
    private static boolean isStax = true;

    public static void stax(boolean isStax) {
        Global.isStax = isStax;
    }

    public static boolean stax() {
        return isStax;
    }

    public static boolean state() {
        return isJDBC;
    }

    public static void state(boolean b) {
        isJDBC = b;
    }
}
