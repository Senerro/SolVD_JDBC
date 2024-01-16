package com.solvd.repairService.service.interfaces;

import com.solvd.repairService.model.AbstractModel;

import java.util.ArrayList;

public interface IService {
    AbstractModel get(Long id);
    void add(AbstractModel model);
    void update(AbstractModel model);
    void delete(AbstractModel model);



}
