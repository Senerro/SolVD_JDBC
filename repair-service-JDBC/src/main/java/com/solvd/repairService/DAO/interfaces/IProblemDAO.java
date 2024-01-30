package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.Equipments;
import com.solvd.repairService.model.Problems;

public interface IProblemDAO extends IAbstractDAO {
    void create(Problems problem);

    boolean linkProblemToEquipment(Problems problems, Equipments equipment);

    boolean updateProblem(Problems problems);

}
