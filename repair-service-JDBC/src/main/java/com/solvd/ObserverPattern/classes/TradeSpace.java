package com.solvd.ObserverPattern.classes;

import com.solvd.ObserverPattern.interfaces.IObservable;
import com.solvd.ObserverPattern.interfaces.IObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TradeSpace implements IObservable {
    TradeEntity exchangeRate;
    List<IObserver> observers;

    public TradeSpace() {
        observers = new ArrayList<>();
        exchangeRate = new TradeEntity();
    }

    public void saleImitation() {
        exchangeRate.USD(new Random().nextInt(21) + 20);

        notifyObservers();
    }

    public boolean endTrades() {
        return observers.isEmpty();
    }

    @Override
    public void registerObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (var element : observers) {
            element.update(exchangeRate);
        }
    }
}
