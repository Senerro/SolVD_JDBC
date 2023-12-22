package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IEquipmentDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.Equipments;

import java.util.List;

public class EquipmentsService {

    public boolean checkAvailability(AbstractModel model) {
        return false;
    }

    public int delete(AbstractModel model) {
        return 0;
    }

    public Equipments create(Equipments equipments) {
        return null;
    }

    public Equipments selectById(Equipments equipments) {
        return null;
    }

    public List<Equipments> selectByType(String type) {
        return null;
    }

    public List<Equipments> selectByProducer(String producer) {
        return null;
    }

    public List<Equipments> orderByCost(double cost, boolean desc) {
        return null;
    }

    public Equipments updateEquipment(Equipments from, Equipments to) {
        return null;
    }
}
