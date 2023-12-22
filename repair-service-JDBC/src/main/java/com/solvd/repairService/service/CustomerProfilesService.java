package com.solvd.repairService.service;

import com.solvd.repairService.DAO.CustomerProfilesDAO;
import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.ICustomerProfileDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.CustomerProfiles;

import java.util.List;

public class CustomerProfilesService {
    private final CustomerProfilesDAO dao = new CustomerProfilesDAO();

    public boolean checkAvailability(AbstractModel model) {
        return dao.checkAvailability(model);
    }


    public int delete(AbstractModel model) {
        return dao.delete(model);
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
