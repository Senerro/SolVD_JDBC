package com.solvd.repairService.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlType(name = "problemType")
@XmlRootElement (name = "problem")
@XmlAccessorType(XmlAccessType.FIELD)
public class Problems extends AbstractModel {
    @XmlElement (name = "description")
    private String description;
    @XmlElement (name = "typeId")
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

    public void description(String description) {
        this.description = description;
    }

    public Long typeId() {
        return typeId;
    }

    public void typeId(Long typeId) {
        this.typeId = typeId;
    }
}
