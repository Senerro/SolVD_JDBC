package com.solvd.repairService.service;

import com.solvd.repairService.helpers.Global;
import com.solvd.repairService.helpers.parsers.JAXB;
import com.solvd.repairService.helpers.parsers.Jackson;
import com.solvd.repairService.model.*;
import com.solvd.repairService.views.AdminView;

import java.util.ArrayList;
import java.util.HashSet;

public class AdminService  {
    public static AbstractModel getPreparedModel(AbstractModel model) {
        if (model.clazz().equals(CustomerProfiles.class)) {
            if (Global.json())
                return Jackson.get(new CustomerProfiles());
            if (Global.jaxb())
                return JAXB.get(new CustomerProfiles());
        }
        if (model.clazz().equals(EmployeeProfiles.class)) {
            if (Global.json())
                return Jackson.get(new EmployeeProfiles());
            if (Global.jaxb())
                return JAXB.get(new EmployeeProfiles());
        }
        if (model.clazz().equals(EmployeePosts.class)) {
            if (Global.json())
                return Jackson.get(new EmployeePosts());
            if (Global.jaxb())
                return JAXB.get(new EmployeePosts());
        }
        if (model.clazz().equals(ServiceCenters.class)) {
            if (Global.json())
                return Jackson.get(new ServiceCenters());
            if (Global.jaxb())
                return JAXB.get(new ServiceCenters());
            if(Global.console())
                return AdminView.createServiceCenter(0L);
        }
        if (model.clazz().equals(Orders.class)) {
            if (Global.json())
                return Jackson.get(new Orders());
            if (Global.jaxb())
                return JAXB.get(new Orders());
        }
        throw new RuntimeException("Illegal class " + model.clazz() + " in getPreparedModel() method");
    }


    public static ServiceCenters createServiceCenter(Long id, String name, String descr, String address) {
        ServiceCenters center = new ServiceCenters(id);
        center.description(descr);
        center.name(name);
        center.address(address);
        return center;
    }

    public static HashSet<Long> getIdFromList(ArrayList<AbstractModel> list) {
        if (!list.isEmpty()) {
            HashSet<Long> set = new HashSet<>();
            for (var element : list)
                set.add(element.id());
            return set;
        }
      else return null;
    }

}
