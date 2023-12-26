package com.solvd.repairService.model;

import java.util.List;

public class Equipments extends AbstractModel {
    private String type;
    private String producer;
    private String model;
    private double price;
    private List<Problems> problemsList;

    public Equipments(Long id) {
        super(id, Equipments.class);
    }

    public Equipments(Long id, String type, String producer, String model, double price) {
        this(id);
        this.type = type;
        this.producer = producer;
        this.model = model;
        this.price = price;

    }

    public Equipments() {
        this(0L);
    }
    public Equipments(String type, String producer, String model, double price)
    {
        this(0L, type, producer, model, price);
    }

    public double price() {
        return price;
    }

    public String type() {
        return type;
    }

    public void type(String equipmentType) {
        this.type = equipmentType;
    }

    public String model() {
        return model;
    }

    public void model(String model) {
        this.model = model;
    }

    public String producer() {
        return producer;
    }

    public void producer(String producer) {
        this.producer = producer;
    }
}
