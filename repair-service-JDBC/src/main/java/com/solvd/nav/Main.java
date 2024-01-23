package com.solvd.nav;

import com.solvd.nav.classes.Graph;
import com.solvd.nav.classes.Node;
import com.solvd.nav.classes.RefWeight;

public class Main {
    public static void main(String[] arg)
    {
        Node minsk = new Node(1L, "Minsk");
        Node borisov = new Node(2L, "Borisov");
        Node vitebsk = new Node(3L, "Vitebsk");
        Node gomel = new Node(4L, "Gomel");
        Node mogilev = new Node(5L, "Mogilev");
        Node grodno = new Node(6L, "Grodno");

        Graph graph = new Graph();
        graph.add(minsk, borisov, new RefWeight(3, 3));
        graph.add(minsk, borisov, new RefWeight(3, 3));
        graph.add(minsk, vitebsk, new RefWeight(7, 7));
        graph.add(vitebsk, gomel, new RefWeight(5, 5));
        graph.add(borisov, gomel, new RefWeight(8, 8));
        graph.add(borisov, mogilev, new RefWeight(1, 1));
        graph.add(mogilev, grodno, new RefWeight(5, 5));
        graph.add(grodno, gomel, new RefWeight(1, 1));

        graph.nodeIterating();
        System.out.println("-----------");
        var a = graph.makeShortPath(gomel, minsk);
        var b = graph.makeFastPath(gomel, minsk);
        for (var element: a) {
            System.out.println(element);
        }
        System.out.println("-----------");
        for (var element: b) {
            System.out.println(element);
        }
    }
}
