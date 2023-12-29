package com.solvd.repairService.DAO.myBatisXML;

import com.solvd.repairService.DAO.JDBC.UsersDAO;
import com.solvd.repairService.DAO.interfaces.IUserDAO;
import com.solvd.repairService.model.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class UserBatisDAO extends UsersDAO implements IUserDAO {
    @Override
    public void create(Users user) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var userDAO = session.getMapper(UsersDAO.class);
            userDAO.create(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
