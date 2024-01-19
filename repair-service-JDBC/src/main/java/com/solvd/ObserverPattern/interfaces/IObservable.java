package com.solvd.ObserverPattern.interfaces;

public interface IObservable {
    void registerObserver(IObserver o);

    void removeObserver(IObserver o);

    void notifyObservers();
}
