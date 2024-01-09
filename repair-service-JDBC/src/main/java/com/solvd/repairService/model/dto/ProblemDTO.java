package com.solvd.repairService.model.dto;


import com.solvd.repairService.model.Problems;

import javax.xml.bind.annotation.*;

import java.util.ArrayList;

@XmlRootElement(name = "problems")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Problems.class})

public class ProblemDTO {
    @XmlAnyElement
    private ArrayList<Problems> list = new ArrayList<>();

    public void addProblems(ArrayList<Problems> list) {
        this.list = list;
    }
}
