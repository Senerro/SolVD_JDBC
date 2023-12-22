package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.AbstractModel;

public interface IAbstractDAO {
    public boolean checkAvailability(AbstractModel model);
    public int delete(AbstractModel model);
}
