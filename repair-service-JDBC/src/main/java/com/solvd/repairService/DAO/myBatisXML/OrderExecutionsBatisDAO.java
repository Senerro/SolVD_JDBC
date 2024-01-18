package com.solvd.repairService.DAO.myBatisXML;

import com.solvd.repairService.DAO.JDBC.OrderExecutionsDAO;
import com.solvd.repairService.DAO.interfaces.IOrderExecuteDAO;
import com.solvd.repairService.model.OrderExecutions;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class OrderExecutionsBatisDAO extends OrderExecutionsDAO implements IOrderExecuteDAO {
    @Override
    public OrderExecutions selectById(OrderExecutions orderExecution) {
        return null;
    }

    @Override
    public void create(OrderExecutions orderExecution) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(OrderExecutionsBatisDAO.class);

            modelDAO.create(orderExecution);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateOrderExecution(OrderExecutions from, OrderExecutions to) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(OrderExecutionsBatisDAO.class);

            modelDAO.updateOrderExecution(from, to);
            return true;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void get(ArrayList<OrderExecutions> list) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(OrderExecutionsBatisDAO.class);

            modelDAO.get(list);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
