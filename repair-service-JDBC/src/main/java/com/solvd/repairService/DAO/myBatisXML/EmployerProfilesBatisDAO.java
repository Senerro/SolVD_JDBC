package com.solvd.repairService.DAO.myBatisXML;

import com.solvd.repairService.DAO.JDBC.EmployerProfilesDAO;
import com.solvd.repairService.DAO.interfaces.IEmployeeProfileDAO;
import com.solvd.repairService.model.EmployeePosts;
import com.solvd.repairService.model.EmployeeProfiles;
import com.solvd.repairService.model.OrderExecutions;
import com.solvd.repairService.model.ServiceCenters;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class EmployerProfilesBatisDAO extends EmployerProfilesDAO implements IEmployeeProfileDAO {
    @Override
    public void create(EmployeeProfiles profile) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(EmployerProfilesBatisDAO.class);

            modelDAO.create(profile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void get(ArrayList<EmployeeProfiles> list) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(EmployerProfilesBatisDAO.class);

            modelDAO.get(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void get(EmployeeProfiles list) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(EmployerProfilesBatisDAO.class);

            modelDAO.get(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeProfiles selectById(EmployeeProfiles profile) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(EmployerProfilesBatisDAO.class);

            return modelDAO.selectById(profile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<EmployeeProfiles> findByFullname(String fullname) {
        throw new NullPointerException();
    }

    @Override
    public List<EmployeeProfiles> findByPhone(String phone) {
        throw new NullPointerException();
    }

    @Override
    public List<EmployeeProfiles> selectByExperience(double role, boolean desc) {
       throw new NullPointerException();
    }

    @Override
    public EmployeeProfiles updateProfile(EmployeeProfiles from, EmployeeProfiles to) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(EmployerProfilesBatisDAO.class);

            return modelDAO.updateProfile(from, to);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeProfiles updatePost(EmployeeProfiles employee, EmployeePosts post) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(EmployerProfilesBatisDAO.class);

            return modelDAO.updatePost(employee, post);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double setCost(EmployeeProfiles employee, OrderExecutions orderExecution) {
        throw new NullPointerException();
    }

    @Override
    public int findFreeByServiceCenter(ServiceCenters center, ArrayList<EmployeeProfiles> list) {
        throw new NullPointerException();
    }

    @Override
    public void findByServiceCenter(ServiceCenters center, ArrayList<EmployeeProfiles> list) {
    throw new NullPointerException();
    }
}
