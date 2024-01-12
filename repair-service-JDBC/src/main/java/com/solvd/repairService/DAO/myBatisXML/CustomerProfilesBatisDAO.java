package com.solvd.repairService.DAO.myBatisXML;

import com.solvd.repairService.DAO.JDBC.CustomerProfilesDAO;
import com.solvd.repairService.DAO.interfaces.ICustomerProfileDAO;
import com.solvd.repairService.model.CustomerProfiles;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CustomerProfilesBatisDAO  extends CustomerProfilesDAO implements ICustomerProfileDAO {
    @Override
    public void create(CustomerProfiles profile) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(CustomerProfilesBatisDAO.class);

            modelDAO.create(profile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void get(CustomerProfiles profile) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(CustomerProfilesBatisDAO.class);

            modelDAO.get(profile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void get(ArrayList<CustomerProfiles> profiles) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(CustomerProfilesBatisDAO.class);

            modelDAO.get(profiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CustomerProfiles> selectByNick(String nick) {
       throw new NullPointerException();
    }

    @Override
    public List<CustomerProfiles> selectByPhone(String phone) {
       throw new NullPointerException();
    }

    @Override
    public CustomerProfiles updateProfile(CustomerProfiles from, CustomerProfiles to) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(CustomerProfilesBatisDAO.class);

           return modelDAO.updateProfile(from, to);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
