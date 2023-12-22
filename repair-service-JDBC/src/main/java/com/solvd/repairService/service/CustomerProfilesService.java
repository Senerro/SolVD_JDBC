package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.ICustomerProfileDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.CustomerProfiles;

import java.util.List;

public class CustomerProfilesService {

    public boolean checkAvailability(AbstractModel model) {
        return false;
    }


    public int delete(AbstractModel model) {
        return 0;
    }


    public CustomerProfiles create(CustomerProfiles profile) {
        return null;
    }


    public CustomerProfiles selectById(CustomerProfiles profile) {
        return null;
    }


    public List<CustomerProfiles> selectByNick(String nick) {
        return null;
    }


    public List<CustomerProfiles> selectByPhone(String phone) {
        return null;
    }


    public CustomerProfiles updateProfile(CustomerProfiles from, CustomerProfiles to) {
        return null;
    }
}
