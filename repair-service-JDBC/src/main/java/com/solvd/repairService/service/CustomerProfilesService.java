package com.solvd.repairService.service;

import com.solvd.repairService.DAO.CustomerProfilesDAO;
import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.ICustomerProfileDAO;
import com.solvd.repairService.DAO.interfaces.IEmployerPostDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.CustomerProfiles;

import java.util.List;

public class CustomerProfilesService {
    private final ICustomerProfileDAO dao;

    public CustomerProfilesService(ICustomerProfileDAO dao) {
        this.dao = dao;
    }

    public boolean checkAvailability(CustomerProfiles model) {
        return dao.checkAvailability(model);
    }

    public int delete(CustomerProfiles model) {
        return dao.delete(model);
    }

    public CustomerProfiles create(CustomerProfiles profile) {
        return dao.create(profile);
    }


    public CustomerProfiles selectById(Long id) {
        return dao.selectById(id);
    }

    public List<CustomerProfiles> selectByNick(String nick) {
        return dao.selectByNick(nick);
    }


    public List<CustomerProfiles> selectByPhone(String phone) {
        return dao.selectByPhone(phone);
    }

    public CustomerProfiles updateProfile(CustomerProfiles from, CustomerProfiles to) {
        return dao.updateProfile(from, to);
    }
}
