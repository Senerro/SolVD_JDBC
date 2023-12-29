package com.solvd.repairService;

import com.solvd.repairService.views.ValidationView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class Main {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static Connection getConnection() throws SQLException, IOException {

        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("src/main/resources/database.properties"))){
            props.load(in);
        }
        return DriverManager.getConnection(
                props.getProperty("url"),
                props.getProperty("username"),
                props.getProperty("password"));
    }

    public static void main(String[] args) {
    ValidationView.loadValidationView();

       /* try{

            var connection = getConnection();
            LOGGER.debug("connection successfully added");


            String sqlCommand = "CREATE TABLE test_table (Id INT PRIMARY KEY AUTO_INCREMENT, ProductName VARCHAR(20), Price INT)";
            Statement statement = connection.createStatement();
            //statement.executeUpdate(sqlCommand);
            //statement.executeUpdate("INSERT test_table (ProductName, Price) VALUES ('iPhone X', 76000)," + "('Galaxy S9', 45000), ('Nokia 9', 36000)");
            //statement.executeUpdate("UPDATE test_table SET Price = Price - 5000");
            //statement.executeUpdate("DELETE FROM test_table WHERE Id = 3");

            PreparedStatement ps = connection.prepareStatement("INSERT test_table (ProductName, Price) VALUES ('test', 540000)", Statement.RETURN_GENERATED_KEYS);
            ResultSet key =  ps.getGeneratedKeys();
//            LOGGER.debug(key.getInt(0));
            ps.executeUpdate();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM test_table");

            while(resultSet.next()){
                int id = resultSet.getInt("iD");
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                LOGGER.debug("id " + id + ", name " + name + ", price " + price);
            }

            connection.close();
        }
        catch(Exception ex){
            LOGGER.debug("Connection failed...\n" + ex);
            System.exit(520);
        }*/
    }
}