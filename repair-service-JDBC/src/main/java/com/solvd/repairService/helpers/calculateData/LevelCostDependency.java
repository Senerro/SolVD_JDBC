package com.solvd.repairService.helpers.calculateData;

public enum LevelCostDependency {
    Junior(0d, 0d, 4d),
    Middle(500d, 5d, 9d),
    Senior(1500d, 10d, 100d),
    ;
    double price;
    double expMin;
    double expMax;

    LevelCostDependency(double price, double expMin, double expMax) {
        this.expMax = expMax;
        this.expMin = expMin;
        this.price = price;
    }

    public double price() {
        return price;
    }

    public double expMax() {
        return expMax;
    }

    public double expMin() {
        return expMin;
    }

    public void expMax(double expMax) {
        this.expMax = expMax;
    }

    public void expMin(double expMin) {
        this.expMin = expMin;
    }

    public void price(double price) {
        this.price = price;
    }

    public LevelCostDependency getLevel(double price) {
        if (price < LevelCostDependency.Middle.price)
            return LevelCostDependency.Junior;
        if ((price < LevelCostDependency.Senior.price))
            return LevelCostDependency.Middle;
        return LevelCostDependency.Senior;
    }
}
