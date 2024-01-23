package com.solvd.nav.helpers;

import com.solvd.nav.classes.RefWeight;

public class RefWeightHelper {
    public static RefWeight distanceSummation(RefWeight first, RefWeight second) {
        return new RefWeight(first.distance() + second.distance(), first.time() + second.time());
    }

    public static RefWeight timeSummation(RefWeight first, RefWeight second) {
        return new RefWeight(first.distance() + second.distance(), first.time() + second.time());
    }

    public static boolean isLessDistance(RefWeight first, RefWeight second) {
        return first.distance() < second.distance();
    }

    public static boolean isLessTime(RefWeight first, RefWeight second) {
        return first.time() < second.time();
    }

    public static RefWeight MAX() {
        return new RefWeight(Float.MAX_VALUE, Float.MAX_VALUE);
    }
    public static 
}
