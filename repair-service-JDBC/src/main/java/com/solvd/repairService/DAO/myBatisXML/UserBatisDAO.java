package com.solvd.repairService.DAO.myBatisXML;

import com.solvd.repairService.DAO.JDBC.UsersDAO;
import com.solvd.repairService.DAO.interfaces.IUserDAO;
import com.solvd.repairService.model.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserBatisDAO extends UsersDAO implements IUserDAO {
    @Override
    public void create(Users user) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var userDAO = session.getMapper(UserBatisDAO.class);
            userDAO.create(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Users findByLogin(String login)
    {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var userDAO = session.getMapper(UserBatisDAO.class);
            Users user = null;
            user = userDAO.findByLogin(login);
            return user;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Users updateUser(Users from, Users to)
    {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var userDAO = session.getMapper(UserBatisDAO.class);
            Users user = null;
            user = userDAO.updateUser(from, to);
            return user;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean deleteUserByLogin(String login) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var userDAO = session.getMapper(UserBatisDAO.class);
            userDAO.deleteUserByLogin(login);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
