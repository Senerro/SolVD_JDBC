package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.EquipmentProblem;

public interface IEquipmentProblemDAO extends IAbstractDAO {
    void create(EquipmentProblem ep);
}
