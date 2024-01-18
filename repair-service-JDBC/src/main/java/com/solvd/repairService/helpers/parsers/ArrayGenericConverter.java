package com.solvd.repairService.helpers.parsers;

import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.EmployeeProfiles;
import com.solvd.repairService.model.Orders;

import java.util.ArrayList;

public class ArrayGenericConverter {
    public static <T extends AbstractModel> ArrayList<T> convert(ArrayList<AbstractModel> models) {
        ArrayList<T> list = new ArrayList<>();
        for (var element:models) {
            list.add((T) element);
        }
        return list;
    }
    public static <T extends AbstractModel> ArrayList<T> convert(ArrayList<AbstractModel> models, Class<T> clazz) {
        ArrayList<T> list = new ArrayList<>();
        for (var element:models) {
            list.add((T) element);
        }
        return list;
    }
    public static<T extends AbstractModel> ArrayList<AbstractModel> reconvert(ArrayList<T > models) {
        ArrayList<AbstractModel> list = new ArrayList<>();
        for (var element:models) {
            list.add( (AbstractModel) element);
        }
        return list;
    }
}
