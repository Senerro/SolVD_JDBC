package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.CustomerProfiles;

import java.util.ArrayList;
import java.util.List;

public interface ICustomerProfileDAO extends IAbstractDAO {
    void create(CustomerProfiles profile);

    void get(CustomerProfiles profile);
    void get(ArrayList<CustomerProfiles> profiles);

    List<CustomerProfiles> selectByNick(String nick);

    List<CustomerProfiles> selectByPhone(String phone);

    CustomerProfiles updateProfile(CustomerProfiles from, CustomerProfiles to);
}
