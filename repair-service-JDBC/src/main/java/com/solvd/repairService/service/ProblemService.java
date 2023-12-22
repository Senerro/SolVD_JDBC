package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IProblemDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.Equipments;
import com.solvd.repairService.model.Problems;

public class ProblemService {
    public boolean checkAvailability(AbstractModel model) {
        return false;
    }
  
    public int delete(AbstractModel model) {
        return 0;
    }
  
    public Problems create(Problems problem) {
        return null;
    }
  
    public boolean linkProblemToEquipment(Problems problems, Equipments equipment) {
        return false;
    }
  
    public boolean updateProblem(Problems problems) {
        return false;
    }
}
