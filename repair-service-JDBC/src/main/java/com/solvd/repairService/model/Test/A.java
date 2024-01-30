package com.solvd.repairService.model.Test;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.ArrayList;
import java.util.HashSet;

public class A {

    private HashSet<B> list = new HashSet<>();
    public A()
    {
        list.add(new B());
        list.add(new B());
        list.add(new B());
    }
    @JsonGetter
    public HashSet<B> getList() {
        return list;
    }
}
