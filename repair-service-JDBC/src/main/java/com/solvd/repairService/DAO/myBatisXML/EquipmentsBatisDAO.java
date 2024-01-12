package com.solvd.repairService.DAO.myBatisXML;

import com.solvd.repairService.DAO.JDBC.EquipmentsDAO;
import com.solvd.repairService.DAO.interfaces.IEquipmentDAO;
import com.solvd.repairService.model.Equipments;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EquipmentsBatisDAO extends EquipmentsDAO implements IEquipmentDAO {
    @Override
    public int create(Equipments equipments) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(EquipmentsBatisDAO.class);

            return modelDAO.create(equipments);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Equipments selectById(Equipments equipments) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(EquipmentsBatisDAO.class);

            return modelDAO.selectById(equipments);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Equipments> selectByType(String type) {
        throw new NullPointerException();
    }

    @Override
    public List<Equipments> selectByProducer(String producer) {
        throw new NullPointerException();
    }

    @Override
    public List<Equipments> orderByCost(double cost, boolean desc) {
        throw new NullPointerException();
    }

    @Override
    public Equipments updateEquipment(Equipments from, Equipments to) {
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(is);
            var session = factory.openSession(true);
            var modelDAO = session.getMapper(EquipmentsBatisDAO.class);

            return modelDAO.updateEquipment(from, to);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
