package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.Equipments;
import com.solvd.repairService.model.Problems;

public interface IProblemDAO extends IAbstractDAO {
    public void create(Problems problem);
    public boolean linkProblemToEquipment(Problems problems, Equipments equipment);
    public boolean updateProblem(Problems problems);

}
