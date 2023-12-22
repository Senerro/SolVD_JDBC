package com.solvd.repairService.model;

import java.util.List;

public class Equipments extends AbstractModel{
    private String equipmentType;
    private String producer;
    private String model;
    private double price;
    private List<Problems> problemsList;

    public Equipments(Long id)
    {
        super(id, Equipments.class);
    }
    public Equipments(Long id, String equipmentType, String producer, double price)
    {
        this(id);
        this.equipmentType = equipmentType;
        this.producer = producer;
        this.price = price;
    }

    public double price() {
        return price;
    }

    public String equipmentType() {
        return equipmentType;
    }

    public String model() {
        return model;
    }
}