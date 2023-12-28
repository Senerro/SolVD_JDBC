package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IEquipmentProblemDAO;
import com.solvd.repairService.model.EquipmentProblem;

public class EquipmentProblemService {
    private IEquipmentProblemDAO dao;

    public EquipmentProblemService(IEquipmentProblemDAO dao) {
        this.dao = dao;
    }

    public EquipmentProblem create(EquipmentProblem ep) {
        dao.create(ep);
        return ep;
    }
}
