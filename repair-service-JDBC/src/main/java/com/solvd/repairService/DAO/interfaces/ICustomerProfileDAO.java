package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.CustomerProfiles;
import com.solvd.repairService.model.Equipments;

import java.util.List;

public interface ICustomerProfileDAO  extends IAbstractDAO {
    public CustomerProfiles create(CustomerProfiles profile);
    public CustomerProfiles selectById(Long id);
    public List<CustomerProfiles> selectByNick(String nick);
    public List<CustomerProfiles> selectByPhone(String phone);
    public CustomerProfiles updateProfile(CustomerProfiles from, CustomerProfiles to);
}
