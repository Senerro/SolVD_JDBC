package com.solvd.nav.classes;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class Node {
    private Long id;
    private final String name;
    private final LinkedHashSet<Ref> refs = new LinkedHashSet<>();
    private final LinkedHashMap<Node, Ref> parents = new LinkedHashMap<>();

    public Node(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long id() {
        return id;
    }

    public void id(Long id) {
        this.id = id;
    }

    public LinkedHashSet<Ref> refs() {
        return refs;
    }

    public LinkedHashMap<Node, Ref> parents() {
        return parents;
    }

    @Override
    public String toString() {
        return "[" + this.id + "] - " + this.name;
    }
}
