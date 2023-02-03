package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.ICleanServiceDAO;
import com.solvd.musichall.models.services.CleanService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CleanServiceDAO extends MySQLDAO implements ICleanServiceDAO {
    private static final Logger LOGGER = LogManager.getLogger(CleanServiceDAO.class);
    private final Connection connection;

    public CleanServiceDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public CleanService getByID(int id) {
        LOGGER.info(String.format("Searching ConcertService with id: %d", id));
        CleanService c = null;
        try {
            String query = "select * from cleanService where idCleanService = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                c = new CleanService(
                        resultSet.getInt("idCleanService"),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getFloat("time"),
                        resultSet.getFloat(("price"))
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return c;
    }

    @Override
    public CleanService create(CleanService cleanService) {
        LOGGER.info(String.format("Creating Cleaning Service, name: %s", cleanService.getName()));
        try {
            String query = "insert into cleanService (name, type, time, price) values (?, ?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cleanService.getName());
            statement.setString(2, cleanService.getType());
            statement.setFloat(3, cleanService.getTime());
            statement.setFloat(4, cleanService.getPrice());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                cleanService.setCleanServiceID(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cleanService;
    }

    @Override
    public CleanService update(CleanService cleanService) {
        LOGGER.info(String.format("Updating Clean Service with id: %d", cleanService.getCleanServiceID()));
        try {
            String query = "update cleanService set name = ?, type = ?, time = ?, price = ?  where idCleanService = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cleanService.getName());
            statement.setString(2, cleanService.getType());
            statement.setFloat(3, cleanService.getTime());
            statement.setFloat(4, cleanService.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cleanService;
    }

    @Override
    public void deleteByID(int id) {
        LOGGER.info(String.format("Deleting clean service with id: %d", id));
        try {
            String query = "Delete from cleanService where idCleanService = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<CleanService> getAll() {
        LOGGER.info("Getting all Cleaning Services");
        ArrayList<CleanService> cleanServices = new ArrayList<>();
        try {
            String query = "Select * from cleanService";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                cleanServices.add(new CleanService(
                        resultSet.getInt("idCleanService"),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getFloat("time"),
                        resultSet.getFloat(("price"))
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cleanServices;
    }

    @Override
    public ArrayList<CleanService> getCleanServicesByScenarioID(int id) {
        LOGGER.info(String.format("Getting all cleaning services by scenario id: %d", id));
        ArrayList<CleanService> cleanServices = new ArrayList<>();
        try {
            String query = "select * from cleaningScenario as c " +
                    "inner join cleanService as cs on c.CleanService_idCleanService = cs.idCleanService " +
                    "where ScenarioRoom_idScenario = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cleanServices.add(new CleanService(
                        resultSet.getInt("idCleanService"),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getFloat("time"),
                        resultSet.getFloat(("price"))
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cleanServices;
    }
}
