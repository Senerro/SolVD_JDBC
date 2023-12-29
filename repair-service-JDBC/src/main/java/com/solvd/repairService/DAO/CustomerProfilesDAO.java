package com.solvd.repairService.DAO;

import com.solvd.repairService.DAO.interfaces.ICustomerProfileDAO;
import com.solvd.repairService.model.CustomerProfiles;

import java.util.List;

public class CustomerProfilesDAO extends AbstractDAO  implements ICustomerProfileDAO {
    @Override
    public CustomerProfiles create(CustomerProfiles profile) {
        return null;
    }

    @Override
    public CustomerProfiles selectById(CustomerProfiles profile) {
        return null;
    }

    @Override
    public List<CustomerProfiles> selectByNick(String nick) {
        return null;
    }

    @Override
    public List<CustomerProfiles> selectByPhone(String phone) {
        return null;
    }

    @Override
    public CustomerProfiles updateProfile(CustomerProfiles from, CustomerProfiles to) {
        return null;
    }
}
