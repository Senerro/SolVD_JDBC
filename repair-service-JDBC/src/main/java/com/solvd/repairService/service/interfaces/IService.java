package com.solvd.repairService.service.interfaces;

import com.solvd.repairService.model.AbstractModel;

import java.util.ArrayList;

public interface IService {
    ArrayList<AbstractModel> get() throws Exception;
    AbstractModel get(Long id);
    void delete(AbstractModel model);



}
