package com.solvd.repairService.DAO.JDBC;

import com.solvd.repairService.DAO.interfaces.IEquipmentDAO;
import com.solvd.repairService.helpers.queryConfigurationHelper.InsertValuesHelper;
import com.solvd.repairService.model.Equipments;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EquipmentsDAO  extends AbstractDAO implements IEquipmentDAO {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }
    private static final Logger LOGGER = LogManager.getLogger(EquipmentsDAO.class);
    @Override
    public int create(Equipments equipment) {
        ArrayList<String>fields = new ArrayList<>();
        ArrayList<String>values = new ArrayList<>();
        fields.add("type");
        values.add(equipment.type());
        fields.add("Producer");
        values.add(equipment.producer());
        fields.add("model");
        values.add(equipment.model());
        fields.add("price");
        values.add(String.valueOf(equipment.price()));
        String query = InsertValuesHelper.get(equipment, fields, values);

        connection = connectionPool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            var a = ps.getGeneratedKeys();
            while (a.next()) {
                equipment.id(a.getLong("GENERATED_KEY"));
            }
            connectionPool.returnConnection(connection);
            ps.close();
            return 1;
        } catch (SQLException e) {
            LOGGER.error("Some error with table " + equipment.tableName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Equipments selectById(Equipments equipments) {
        return null;
    }

    @Override
    public List<Equipments> selectByType(String type) {
        return null;
    }

    @Override
    public List<Equipments> selectByProducer(String producer) {
        return null;
    }

    @Override
    public List<Equipments> orderByCost(double cost, boolean desc) {
        return null;
    }

    @Override
    public Equipments updateEquipment(Equipments from, Equipments to) {
        return null;
    }
}
