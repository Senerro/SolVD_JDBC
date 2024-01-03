package com.solvd.repairService.DAO.JDBC;

import com.solvd.repairService.DAO.interfaces.IProblemDAO;
import com.solvd.repairService.helpers.queryConfigurationHelper.InsertValuesHelper;
import com.solvd.repairService.model.Equipments;
import com.solvd.repairService.model.Problems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProblemsDAO extends AbstractDAO implements IProblemDAO {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(ProblemsDAO.class);

    @Override
    public void create(Problems problem) {
        ArrayList<String> fields = new ArrayList<>();
        fields.add("typeId");

        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(problem.typeId()));


        String query = InsertValuesHelper.get(problem, fields, values);
        ResultSet result;
        PreparedStatement ps;
        connection = connectionPool.getConnection();
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            result = ps.getGeneratedKeys();
            connectionPool.returnConnection(connection);
            while (result.next()) {
                problem.id(result.getLong("GENERATED_KEY"));
            }
            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Some error with table " + problem.tableName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean linkProblemToEquipment(Problems problems, Equipments equipment) {
        return false;
    }

    @Override
    public boolean updateProblem(Problems problems) {
        return false;
    }
}
