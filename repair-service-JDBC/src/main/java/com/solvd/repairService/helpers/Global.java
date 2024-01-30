package com.solvd.repairService.helpers;

public class Global {
    private static boolean isConsole = false;
    private static boolean isJAXB = false;
    private static boolean isJson = false;
    private static boolean isJDBC = true;

    public static boolean jdbc() {
        return isJDBC;
    }

    public static void jaxb(boolean isStax) {
        Global.isJAXB = isStax;
    }

    public static boolean jaxb() {
        return isJAXB;
    }

    public static boolean console() {
        return isConsole;
    }

    public static void console(boolean b) {
        isConsole = b;
    }

    public static boolean json() {
        return isJson;
    }

    public static void json(boolean b) {
        isJson = b;
    }

}
