package com.solvd.repairService.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
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

    @JsonGetter
    public String description() {
        return description;
    }

    public void description(String description) {
        this.description = description;
    }

    @JsonGetter
    public Long typeId() {
        return typeId;
    }

    @JsonGetter
    public void typeId(Long typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "id = " + id() +" , typeId=" + typeId;
    }
}
