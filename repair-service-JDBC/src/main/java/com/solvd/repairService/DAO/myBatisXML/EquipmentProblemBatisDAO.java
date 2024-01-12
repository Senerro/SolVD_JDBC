package com.solvd.repairService.DAO.myBatisXML;

import com.solvd.repairService.DAO.JDBC.EquipmentProblemDAO;
import com.solvd.repairService.DAO.interfaces.IEquipmentProblemDAO;
import com.solvd.repairService.model.EquipmentProblem;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class EquipmentProblemBatisDAO extends EquipmentProblemDAO  implements IEquipmentProblemDAO {
    @Override
    public void create(EquipmentProblem ep) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(EquipmentProblemBatisDAO.class);

             modelDAO.create(ep);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
