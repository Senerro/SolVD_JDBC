package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.CustomerProfiles;
import com.solvd.repairService.model.Equipments;

import java.util.List;

public interface ICustomerProfileDAO {
    public CustomerProfiles create(CustomerProfiles profile);
    public CustomerProfiles selectById(CustomerProfiles profile);
    public List<CustomerProfiles> selectByNick(String nick);
    public List<CustomerProfiles> selectByPhone(String phone);
    public CustomerProfiles updateProfile(CustomerProfiles from, CustomerProfiles to);
}
