package com.solvd.repairService.model.Test;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class B {
    private double number;
    private String string;
    public B() {
        this.number = Math.random();
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        this.string = "sddf";
    }
    @JsonGetter
    public double getNumber() {
        return number;
    }

    @JsonGetter
    public String getString() {
        return string;
    }
}
