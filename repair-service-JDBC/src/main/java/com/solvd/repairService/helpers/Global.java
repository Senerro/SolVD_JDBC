package com.solvd.repairService.helpers;

public class Global {
    private static boolean isJDBC = true;
    private static boolean isJAXB = true;

    public static void jaxb(boolean isStax) {
        Global.isJAXB = isStax;
    }

    public static boolean jaxb() {
        return isJAXB;
    }

    public static boolean state() {
        return isJDBC;
    }

    public static void state(boolean b) {
        isJDBC = b;
    }
}
