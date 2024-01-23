package com.solvd.nav.classes;

public class Ref {
    private Node neighbour;
    private RefWeight weight;


    public Ref(Node neighbour, final RefWeight weight) {
        this(weight);
        this.neighbour = neighbour;
    }

    public Ref(final RefWeight weight) {
        this.weight = weight;
    }

    public RefWeight weight()
    {
        return this.weight;
    }

    public Node neighbour() {
        return neighbour;
    }

    public void neighbour(Node neighbour) {
        this.neighbour = neighbour;
    }

}
