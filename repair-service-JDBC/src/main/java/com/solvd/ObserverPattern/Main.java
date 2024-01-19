package com.solvd.ObserverPattern;

import com.solvd.ObserverPattern.classes.TradeRobot;
import com.solvd.ObserverPattern.classes.TradeSpace;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        TradeSpace burse = new TradeSpace();

        ArrayList<TradeRobot> robots = new ArrayList<>();

        TradeRobot broker = new TradeRobot("FIRST", burse, 500, 5);
        robots.add(broker);

        TradeRobot broker2 = new TradeRobot("[Second]", burse, 500, 7);
        robots.add(broker2);

       /* while (!burse.endTrades() )
        {
            burse.saleImitation();
        }*/

        burse.saleImitation();
        broker.stopTrade();
        burse.saleImitation();
        burse.saleImitation();
        broker2.stopTrade();
        burse.saleImitation();
    }
}
