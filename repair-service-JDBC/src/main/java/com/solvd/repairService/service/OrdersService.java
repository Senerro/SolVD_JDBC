package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IOrderDAO;
import com.solvd.repairService.model.*;
import com.solvd.repairService.service.interfaces.IService;

import java.util.ArrayList;
import java.util.List;

public class OrdersService implements IService {
    private final IOrderDAO dao;

    public OrdersService(IOrderDAO dao) {
        this.dao = dao;
    }

    public boolean checkAvailability(Orders model) {
        return dao.checkAvailability(model);
    }

    public int delete(Orders model) {
        return dao.delete(model);
    }

    public Orders create(CustomerProfiles profile, Equipments equipment, OrderExecutions orderExecution) {
        var order = new Orders(profile.id(), equipment, orderExecution);
        dao.create(order);

        return order;
    }

    public Orders selectById(Long id) {
        Orders order = new Orders(id);
        dao.selectById(order);
        return order;
    }

    public ArrayList<Orders> ordersHistory(CustomerProfiles profiles) {
        return dao.orderHistory(profiles);

    }
    public ArrayList<AbstractModel> get() {
        ArrayList<Orders> orders = new ArrayList<>();
        dao.get(orders);
        return new ArrayList<>(orders);
    }

    public Orders get(Long id) {
        Orders order = new Orders(id);
        dao.get(order);
        return order;
    }
    @Override
    public void delete(AbstractModel model) {

    }
    @Override
    public void update(AbstractModel previos, AbstractModel next) {
        update((Orders) previos, (Orders) next);
    }

    public void update(Orders order, Orders newOrder) {
        dao.update(order, newOrder);
    }
}
