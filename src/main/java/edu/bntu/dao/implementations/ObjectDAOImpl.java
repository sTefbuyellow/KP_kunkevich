package edu.bntu.dao.implementations;

import com.mysql.cj.jdbc.ConnectionImpl;
import edu.bntu.dao.interfaces.ObjectDAO;
import edu.bntu.model.PartObject;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.logging.Logger;

public class ObjectDAOImpl implements ObjectDAO {

    public static String JDBC_URL = "jdbc:mysql://localhost:3306";
    public static String JDBC_USER = "admin";
    public static String JDBC_PASSWORD = "BlackBlood06";

    private static Connection connection;

    private static final Logger logger = Logger.getLogger(ObjectDAOImpl.class.getName());


    public ObjectDAOImpl(){
    }

    public void openConnection() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            logger.warning("Connection opening exception");
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.warning("Connection closing exception");
        }
    }

    @Override
    public PartObject getById(Integer id) {
        return null;
    }

    @Override
    public Collection<PartObject> getAll() {
        try {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from kp_schema.object");
            ArrayList<PartObject> parts = getPartObjects(resultSet);
            resultSet.close();
            statement.close();
            closeConnection();
            if(parts.size() == 0)
                return new ArrayList<>();
            return parts;
        } catch (SQLException e) {
            logger.warning("Exception during parts select");
            return new ArrayList<>();
        }
    }

    @Override
    public Collection<PartObject> search(String searchString) {
        try {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from kp_schema.object where " + searchString);
            ArrayList<PartObject> parts = getPartObjects(resultSet);
            resultSet.close();
            statement.close();
            closeConnection();
            if(parts.size() == 0)
                return new ArrayList<>();
            return parts;
        } catch (SQLException e) {
            logger.warning("Exception during parts select");
            return new ArrayList<>();
        }
    }

    private ArrayList<PartObject> getPartObjects(ResultSet resultSet) {
        try {
            ArrayList<PartObject> partObjects = new ArrayList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String series = resultSet.getString("series");
                String manufacturer = resultSet.getString("manufacturer");
                Integer forceH = resultSet.getInt("force_H");
                PartObject partObject = new PartObject(id, name, series, manufacturer, forceH);
                partObjects.add(partObject);
            }
            return partObjects;
        } catch (SQLException e) {
            logger.warning("Exception during result set parsing");
        }
        return new ArrayList<>();
    }

}
