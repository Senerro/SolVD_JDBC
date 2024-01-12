package com.solvd.repairService.DAO.myBatisXML;

import com.solvd.repairService.DAO.JDBC.EmployerPostsDAO;
import com.solvd.repairService.DAO.interfaces.IEmployeePostDAO;
import com.solvd.repairService.model.EmployeePosts;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class EmployerPostsBatisDAO extends EmployerPostsDAO implements IEmployeePostDAO {
    @Override
    public void create(EmployeePosts post) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(EmployerPostsBatisDAO.class);

            modelDAO.create(post);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeePosts selectById(EmployeePosts post) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(EmployerPostsBatisDAO.class);

            return modelDAO.selectById(post);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeePosts changePostName(EmployeePosts from, EmployeePosts to) {
        throw new NullPointerException();
    }

    @Override
    public void get(ArrayList<EmployeePosts> list) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(EmployerPostsBatisDAO.class);

            modelDAO.get(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void get(EmployeePosts post) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(EmployerPostsBatisDAO.class);

            modelDAO.get(post);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(EmployeePosts post, EmployeePosts newPost) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(EmployerPostsBatisDAO.class);

            modelDAO.update(post, newPost);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
