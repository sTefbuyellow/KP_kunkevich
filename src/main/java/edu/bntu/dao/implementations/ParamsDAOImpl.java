package edu.bntu.dao.implementations;

import edu.bntu.dao.interfaces.ParamsDAO;
import edu.bntu.model.Geometry;
import edu.bntu.model.Parameter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class ParamsDAOImpl implements ParamsDAO {

    public static String JDBC_URL = "jdbc:mysql://localhost:3306";
    public static String JDBC_USER = "admin";
    public static String JDBC_PASSWORD = "BlackBlood06";

    private static Connection connection;

    private static final Logger logger = Logger.getLogger(ParamsDAOImpl.class.getName());


    public ParamsDAOImpl(){
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
    public Collection<Parameter> getAllByObjectId(Integer objectId) {
        try {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from kp_schema.params where object_id =" + objectId);
            ArrayList<Parameter> parts = getParams(resultSet);
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

    private ArrayList<Parameter> getParams(ResultSet resultSet) {
        try {
            ArrayList<Parameter> params = new ArrayList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer objectPartId = resultSet.getInt("object_id");
                String name = resultSet.getString("name");
                String value = resultSet.getString("value");
                Parameter parameter = new Parameter(id, objectPartId, name, value);
                params.add(parameter);
            }
            return params;
        } catch (SQLException e) {
            logger.warning("Exception during result set parsing");
        }
        return new ArrayList<>();
    }
}
