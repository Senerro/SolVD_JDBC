package com.solvd.repairService.DAO;

import com.solvd.repairService.DAO.interfaces.IEquipmentDAO;
import com.solvd.repairService.model.Equipments;

import java.util.List;

public class EquipmentsDAO  extends AbstractDAO implements IEquipmentDAO {
    @Override
    public Equipments create(Equipments equipments) {
        return null;
    }

    @Override
    public Equipments selectById(Equipments equipments) {
        return null;
    }

    @Override
    public List<Equipments> selectByType(String type) {
        return null;
    }

    @Override
    public List<Equipments> selectByProducer(String producer) {
        return null;
    }

    @Override
    public List<Equipments> orderByCost(double cost, boolean desc) {
        return null;
    }

    @Override
    public Equipments updateEquipment(Equipments from, Equipments to) {
        return null;
    }
}
