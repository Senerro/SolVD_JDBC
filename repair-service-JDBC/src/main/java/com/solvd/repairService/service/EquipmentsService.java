package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IEquipmentDAO;
import com.solvd.repairService.DAO.interfaces.IOrderExecuteDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.Equipments;

import java.util.List;

public class EquipmentsService {

    private final IEquipmentDAO dao;

    public EquipmentsService(IEquipmentDAO dao) {
        this.dao = dao;
    }

    public boolean checkAvailability(Equipments model) {
        return dao.checkAvailability(model);
    }

    public int delete(Equipments model) {
        return dao.delete(model);
    }

    public Equipments create(Equipments equipment) {
        return dao.create(equipment);
    }

    public Equipments selectById(Equipments equipment) {
        return dao.selectById(equipment);
    }

    public List<Equipments> selectByType(String type) {
        return dao.selectByType(type);
    }

    public List<Equipments> selectByProducer(String producer) {
        return dao.selectByProducer(producer);
    }

    public List<Equipments> orderByCost(double cost, boolean desc) {
        return dao.orderByCost(cost, desc);
    }

    public Equipments updateEquipment(Equipments from, Equipments to) {
        return dao.updateEquipment(from, to);
    }
}
