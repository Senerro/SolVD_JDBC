package com.solvd.repairService.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.solvd.repairService.model.dto.ProblemDTO;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlType(name = "equipment")
@XmlRootElement
@XmlSeeAlso({Problems.class, ProblemDTO.class})
@XmlAccessorType(XmlAccessType.FIELD)

public class Equipments extends AbstractModel {
    @XmlElement
    private String type;
    @XmlElement
    private String producer;
    @XmlElement
    private String model;
    @XmlElement
    private double price;
    private List<Problems> problemsList = new ArrayList<>();
    @XmlElement (name = "list")
    private ProblemDTO problemDTO;

    @JsonGetter
    public ProblemDTO problemDTO() {
        return problemDTO;
    }
    public void problemDTO(ProblemDTO problemDTO) {
       this.problemDTO = problemDTO;
    }

    public Equipments(Long id) {
        super(id, Equipments.class);
    }

    public Equipments(Equipments equipment) {
        super(equipment.id(), Equipments.class);
        this.producer = equipment.producer;
        this.model = equipment.model;
        this.price = equipment.price;
        this.problemsList = equipment.problemsList;
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

    public Equipments(String type, String producer, String model, double price) {
        this(0L, type, producer, model, price);
    }

    @JsonGetter
    public double price() {
        return price;
    }
    public void price(Double price) {
        this.price = price;
    }

    @JsonGetter
    public String type() {
        return type;
    }

    public void type(String equipmentType) {
        this.type = equipmentType;
    }

    @JsonGetter
    public String model() {
        return model;
    }

    public void model(String model) {
        this.model = model;
    }

    @JsonGetter
    public String producer() {
        return producer;
    }

    public void producer(String producer) {
        this.producer = producer;
    }

    public void addProblem(Problems problem) {
        problemsList.add(problem);
    }
    public Problems getProblem() {
        return problemsList.get(problemsList.size()-1);
    }
    @JsonGetter

    public ArrayList<Problems> getProblems()
    {
        return (ArrayList<Problems>) this.problemsList;
    }
}
