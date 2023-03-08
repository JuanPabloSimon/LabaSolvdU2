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

    @Override
    public CleanService getByID(int id) {
        LOGGER.info(String.format("Searching ConcertService with id: %d", id));
        CleanService c = null;
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_BY_ID = "SELECT * FROM cleanService WHERE idCleanService = ?";
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
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
        try (Connection connection = MySQLDAO.getConnection();) {
            String CREATE = "INSER INTO cleanService (name, type, time, price) VALUES (?, ?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
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
        try (Connection connection = MySQLDAO.getConnection();) {
            String UPDATE = "UPDATE cleanService SET name = ?, type = ?, time = ?, price = ?  WHERE idCleanService = ?";
            PreparedStatement statement = connection.prepareStatement(UPDATE);
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
        try (Connection connection = MySQLDAO.getConnection();) {
            String DELETE_BY_ID = "DELETE FROM cleanService WHERE idCleanService = ?";
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
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
        try (Connection connection = MySQLDAO.getConnection();) {
            Statement statement = connection.createStatement();
            String GET_ALL = "SELECT * FROM cleanService";
            ResultSet resultSet = statement.executeQuery(GET_ALL);
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
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_BY_SCENARIO_ID = "SELECT * FROM cleaningScenario AS c " +
                    "INNER JOIN cleanService AS cs ON c.CleanService_idCleanService = cs.idCleanService " +
                    "WHERE ScenarioRoom_idScenario = ?";
            PreparedStatement statement = connection.prepareStatement(GET_BY_SCENARIO_ID);
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
