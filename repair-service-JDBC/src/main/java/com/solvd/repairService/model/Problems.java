package com.solvd.repairService.model;

import java.util.List;

public class Problems extends AbstractModel {
private String description;
private List<Equipments> equipmentsList;
    public Problems(Long id) {
        super(id, Problems.class);
    }

    public Problems(Long id, String description) {
        this(id);
        this.description = description;
    }

    public String description() {
        return description;
    }

}
