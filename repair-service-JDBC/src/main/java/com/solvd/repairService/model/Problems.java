package com.solvd.repairService.model;

import java.util.List;

public class Problems extends AbstractModel {
private String description;
private Long typeId;
private List<Equipments> equipmentsList;
    public Problems(Long id) {
        super(id, Problems.class);
    }
    public Problems() {
        super(0L, Problems.class);
    }


    public Problems(Long id, Long typeId) {
        this(id);
        this.typeId = typeId;
    }

    public String description() {
        return description;
    }
    public void description( String description) {
        this.description = description;
    }
    public Long typeId() {
        return typeId;
    }

    public void typeId(Long typeId) {
        this.typeId = typeId;
    }
}
