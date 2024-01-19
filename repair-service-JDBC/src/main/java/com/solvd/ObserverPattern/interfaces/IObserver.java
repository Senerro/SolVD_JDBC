package com.solvd.ObserverPattern.interfaces;

import com.solvd.ObserverPattern.classes.TradeEntity;

public interface IObserver {
    void update(TradeEntity ob);
    void stopTrade();
}
