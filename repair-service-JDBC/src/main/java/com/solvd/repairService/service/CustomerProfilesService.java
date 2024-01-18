package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.ICustomerProfileDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.CustomerProfiles;
import com.solvd.repairService.model.ServiceCenters;
import com.solvd.repairService.service.interfaces.IService;

import java.util.ArrayList;
import java.util.List;

public class CustomerProfilesService implements IService {
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
         dao.create(profile);
         return profile;
    }

    public CustomerProfiles get(Long id) {
        CustomerProfiles profile = new CustomerProfiles(id);
         dao.get(profile);
         return profile;
    }

    @Override
    public void delete(AbstractModel model) {

    }

    @Override
    public void update(AbstractModel previos, AbstractModel next) {
        try {
            updateProfile((CustomerProfiles) previos, (CustomerProfiles) next);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<CustomerProfiles> selectByNick(String nick) {
        return dao.selectByNick(nick);
    }


    public List<CustomerProfiles> selectByPhone(String phone) {
        return dao.selectByPhone(phone);
    }

    public CustomerProfiles updateProfile(CustomerProfiles from, CustomerProfiles to) throws Exception {
        if(from.equals(to))
            throw new Exception("similar profiles. Id " + from.user().id());

        return dao.updateProfile(from, to);
    }
   /* public ArrayList<CustomerProfiles> get() throws Exception {
        ArrayList<CustomerProfiles>  profiles = new ArrayList<>();
        dao.get(profiles);
        if(profiles.isEmpty())
            throw new Exception("empty customers list");
        return profiles;
    }*/
    @Override
    public ArrayList<AbstractModel> get() throws Exception {
        ArrayList<CustomerProfiles>  profiles = new ArrayList<>();
        dao.get(profiles);
        if(profiles.isEmpty())
            throw new Exception("empty customers list");

        return new ArrayList<>(profiles);
    }
}
