package com.solvd.repairService.model;

public class EquipmentProblem extends AbstractModel {
    private Long equipmentId;
    private Long problemId;

    public EquipmentProblem(Long equipmentId, Long problemId) {
        super(0L, EquipmentProblem.class);
        this.equipmentId = equipmentId;
        this.problemId = problemId;
    }

    public Long equipmentId() {
        return equipmentId;
    }

    public void equipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Long problemId() {
        return problemId;
    }

    public void problemId(Long problemId) {
        this.problemId = problemId;
    }
}
