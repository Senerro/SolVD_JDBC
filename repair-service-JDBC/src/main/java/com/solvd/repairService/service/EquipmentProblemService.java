package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IEquipmentProblemDAO;
import com.solvd.repairService.model.EquipmentProblem;
import com.solvd.repairService.model.Equipments;
import com.solvd.repairService.model.Problems;

public class EquipmentProblemService {
    private IEquipmentProblemDAO dao;

    public EquipmentProblemService(IEquipmentProblemDAO dao) {
        this.dao = dao;
    }

    public EquipmentProblem create(Equipments equipment, Problems problem) {
        EquipmentProblem ep = new EquipmentProblem(equipment.id(), problem.id());
        dao.create(ep);
        return ep;
    }
}
