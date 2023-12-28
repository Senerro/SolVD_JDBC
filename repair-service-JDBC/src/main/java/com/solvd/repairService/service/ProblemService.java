package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IProblemDAO;
import com.solvd.repairService.DAO.interfaces.IServiceCenterDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.Equipments;
import com.solvd.repairService.model.Problems;

import java.util.Random;

public class ProblemService {
    private final IProblemDAO dao;

    public ProblemService(IProblemDAO dao) {
        this.dao = dao;
    }

    public boolean checkAvailability(Problems model) {
        return dao.checkAvailability(model);
    }

    public int delete(Problems model) {
        return dao.delete(model);
    }

    public Problems create() {
        Problems problem = new Problems();
        problem.typeId(new Random().nextLong(3) + 1);
        dao.create(problem);
        return problem;
    }

    public boolean linkProblemToEquipment(Problems problem, Equipments equipment) {
        return dao.linkProblemToEquipment(problem, equipment);
    }

    public boolean updateProblem(Problems problem) {
        return dao.updateProblem(problem);
    }
}
