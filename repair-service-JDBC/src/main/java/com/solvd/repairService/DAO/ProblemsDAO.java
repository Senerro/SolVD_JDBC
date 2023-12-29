package com.solvd.repairService.DAO;

import com.solvd.repairService.DAO.interfaces.IProblemDAO;
import com.solvd.repairService.model.Equipments;
import com.solvd.repairService.model.Problems;

public class ProblemsDAO  extends AbstractDAO implements IProblemDAO {
    @Override
    public Problems create(Problems problem) {
        return null;
    }

    @Override
    public boolean linkProblemToEquipment(Problems problems, Equipments equipment) {
        return false;
    }

    @Override
    public boolean updateProblem(Problems problems) {
        return false;
    }
}
