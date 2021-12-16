package edu.bntu.dao.implementations;

import edu.bntu.dao.interfaces.GeometryDAO;
import edu.bntu.model.Geometry;
import edu.bntu.model.Parameter;
import edu.bntu.model.PartObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class GeometryDAOImpl implements GeometryDAO {

    public static String JDBC_URL = "jdbc:mysql://localhost:3306";
    public static String JDBC_USER = "admin";
    public static String JDBC_PASSWORD = "BlackBlood06";

    private static Connection connection;

    private static final Logger logger = Logger.getLogger(GeometryDAOImpl.class.getName());


    public GeometryDAOImpl(){
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
    public Geometry getByObjectId(Integer objectId) {
        try {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select * from kp_schema.geometry where object_id =" + objectId);
            ArrayList<Geometry> parts = getGeometry(resultSet);
            resultSet.close();
            statement.close();
            closeConnection();
            if(parts.size() == 0)
                return null;
            return parts.get(0);
        } catch (SQLException e) {
            logger.warning("Exception during parts select");
            return null;
        }
    }

    private ArrayList<Geometry> getGeometry(ResultSet resultSet) {
        try {
            ArrayList<Geometry> geometryArrayList = new ArrayList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer objectPartId = resultSet.getInt("object_id");
                Integer height = resultSet.getInt("height");
                Integer width = resultSet.getInt("width");
                Integer length = resultSet.getInt("length");
                Integer weight = resultSet.getInt("weight");
                Geometry geometry = new Geometry(id, objectPartId, height, width, length, weight);
                geometryArrayList.add(geometry);
            }
            return geometryArrayList;
        } catch (SQLException e) {
            logger.warning("Exception during result set parsing");
        }
        return new ArrayList<>();
    }
}
