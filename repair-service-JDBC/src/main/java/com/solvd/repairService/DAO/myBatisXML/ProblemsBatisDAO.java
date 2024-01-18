package com.solvd.repairService.DAO.myBatisXML;

import com.solvd.repairService.DAO.JDBC.ProblemsDAO;
import com.solvd.repairService.DAO.interfaces.IProblemDAO;
import com.solvd.repairService.model.Equipments;
import com.solvd.repairService.model.Problems;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class ProblemsBatisDAO extends ProblemsDAO implements IProblemDAO {
    @Override
    public void create(Problems problem) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(ProblemsBatisDAO.class);

            modelDAO.create(problem);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean linkProblemToEquipment(Problems problems, Equipments equipment) {
        throw new NullPointerException();
    }

    @Override
    public boolean updateProblem(Problems problems) {
        throw new NullPointerException();
    }
}
