package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.Equipments;
import com.solvd.repairService.model.Users;

import java.util.List;

public interface IEquipmentDAO extends IAbstractDAO  {
    public Equipments create(Equipments equipments);
    public Equipments selectById(Equipments equipments);
    public List<Equipments> selectByType(String type);
    public List<Equipments> selectByProducer(String producer);
    public List<Equipments> orderByCost(double cost, boolean desc);
    public Equipments updateEquipment(Equipments from, Equipments to);
}
