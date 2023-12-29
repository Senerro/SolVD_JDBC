package com.solvd.repairService;

import com.solvd.repairService.DAO.UsersDAO;
import com.solvd.repairService.model.CustomerProfiles;
import com.solvd.repairService.model.Users;

public class testMAin {
    public static void main(String[] args) {
        UsersDAO uda = new UsersDAO();
        var a = uda.checkAvailability(new CustomerProfiles(6L));
        if(a)
            System.out.printf("deleted");
        else System.out.printf("not deleted");

    }
}
