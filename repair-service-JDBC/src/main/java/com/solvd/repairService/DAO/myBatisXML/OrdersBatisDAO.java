package com.solvd.repairService.DAO.myBatisXML;

import com.solvd.repairService.DAO.JDBC.OrdersDAO;
import com.solvd.repairService.DAO.interfaces.IOrderDAO;
import com.solvd.repairService.model.CustomerProfiles;
import com.solvd.repairService.model.EmployeeProfiles;
import com.solvd.repairService.model.Orders;
import com.solvd.repairService.model.ServiceCenters;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class OrdersBatisDAO extends OrdersDAO implements IOrderDAO {
    @Override
    public void create(Orders order) {

        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(OrdersBatisDAO.class);

            modelDAO.create(order);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void selectById(Orders order) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(OrdersBatisDAO.class);

            modelDAO.selectById(order);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Orders changeRepairman(Orders order, EmployeeProfiles repairman) {
        return null;
    }

    @Override
    public Orders changeServiceCenter(Orders order, ServiceCenters serviceCenter) {
        return null;
    }

    @Override
    public List<Orders> orderHistory(CustomerProfiles profiles) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(OrdersBatisDAO.class);

            return modelDAO.orderHistory(profiles);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void get(ArrayList<Orders> list) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(OrdersBatisDAO.class);

            modelDAO.get(list);
            session.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void get(Orders order) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(OrdersBatisDAO.class);

            modelDAO.get(order);
            session.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Orders order, Orders newOrder) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(OrdersBatisDAO.class);

            modelDAO.update(order, newOrder);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
