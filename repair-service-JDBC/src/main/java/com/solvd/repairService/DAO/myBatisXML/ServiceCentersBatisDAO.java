package com.solvd.repairService.DAO.myBatisXML;

import com.solvd.repairService.DAO.JDBC.ServiceCentersDAO;
import com.solvd.repairService.DAO.JDBC.UsersDAO;
import com.solvd.repairService.DAO.interfaces.IServiceCenterDAO;
import com.solvd.repairService.model.ServiceCenters;
import com.solvd.repairService.model.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ServiceCentersBatisDAO extends ServiceCentersDAO implements IServiceCenterDAO {
    @Override
    public void create(ServiceCenters serviceCenter)
    {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(ServiceCentersBatisDAO.class);

            modelDAO.create(serviceCenter);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(ServiceCenters from, ServiceCenters to) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(ServiceCentersBatisDAO.class);

            modelDAO.update(from, to);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public List<ServiceCenters> orderByBusing(boolean desc) {
        throw new NullPointerException();
    }

    @Override
    public void findUnoccupied(ServiceCenters centers) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(ServiceCentersBatisDAO.class);

            modelDAO.findUnoccupied(centers);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findCrowded(ServiceCenters centers) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(ServiceCentersBatisDAO.class);

            modelDAO.findCrowded(centers);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void get(ArrayList<ServiceCenters> serviceCenters) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(ServiceCentersBatisDAO.class);

            modelDAO.get(serviceCenters);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void get(ServiceCenters center) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(ServiceCentersBatisDAO.class);

            modelDAO.create(center);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
