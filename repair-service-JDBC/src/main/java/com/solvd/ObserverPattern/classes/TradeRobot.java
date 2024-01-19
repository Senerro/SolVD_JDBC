package com.solvd.ObserverPattern.classes;

import com.solvd.ObserverPattern.interfaces.IObservable;
import com.solvd.ObserverPattern.interfaces.IObserver;

public class TradeRobot implements IObserver {
    private String name;
    private IObservable burse;
    private int currentCapital;
    private int sellOperation;

    public TradeRobot(String name, IObservable burse) {
        this.name = name;
        this.burse = burse;
        burse.registerObserver(this);
    }

    public TradeRobot(String name, IObservable burse, int startCapital, int sellOperation) {
        this.name = name;
        this.burse = burse;
        burse.registerObserver(this);
        this.currentCapital = startCapital;
        this.sellOperation = sellOperation;
    }

    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    @Override
    public void update(TradeEntity ob) {
        sellOperation--;
        if (ob.USD() > 30) {
            sell(ob);
        } else if (currentCapital > ob.USD()) {
            buy(ob);
        }
        if (sellOperation <= 0)
            stopTrade();
    }

    private void sell(TradeEntity ob) {
        System.out.printf("Robot " + this.name + " sell the dollars; cost: " + ob.USD() + "\n");
        currentCapital += ob.USD();
    }

    private void buy(TradeEntity ob) {
        System.out.printf("Robot " + this.name + " buy  the dollars; cost: " + ob.USD() + "\n");
        currentCapital -= ob.USD();
    }

    public void stopTrade() {
        burse.removeObserver(this);
        burse = null;
    }
}
