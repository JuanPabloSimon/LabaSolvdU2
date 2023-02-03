package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.IConcertServices;
import com.solvd.musichall.models.services.ConcertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConcertServicesDAO extends MySQLDAO implements IConcertServices {
    private static final Logger LOGGER = LogManager.getLogger(ConcertServicesDAO.class);
    private final Connection connection;

    public ConcertServicesDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ConcertService getByID(int id) {
        LOGGER.info(String.format("Searching ConcertService with id: %d", id));
        ConcertService c = null;
        try {
            String query = "select * from concertService where idConcertService = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                c = new ConcertService(
                        resultSet.getInt("idConcertService"),
                        resultSet.getString("serviceName"),
                        resultSet.getString("type")
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return c;
    }

    @Override
    public ConcertService create(ConcertService concertService) {
        LOGGER.info(String.format("Creating Concert Service, name: %s", concertService.getName()));
        try {
            String query = "insert into concertService (serviceName, type) values (?, ?) ";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, concertService.getName());
            statement.setString(2, concertService.getType());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                concertService.setConcertServiceID(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return concertService;
    }

    @Override
    public ConcertService update(ConcertService concertService) {
        LOGGER.info(String.format("Updating Concert Service with id: %d", concertService.getConcertServiceID()));
        try {
            String query = "update concertService set serviceName = ?, type = ? where idConcertService = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, concertService.getName());
            statement.setString(2, concertService.getType());
            statement.setInt(3, concertService.getConcertServiceID());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return concertService;
    }

    @Override
    public void deleteByID(int id) {
        LOGGER.info(String.format("Deleting concertService with id: %d", id));
        try {
            String query = "Delete from concertService where idConcertService = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<ConcertService> getAll() {
        LOGGER.info("Getting all concertServices");
        ArrayList<ConcertService> concertServices = new ArrayList<>();
        try {
            String query = "Select * from concertService";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                concertServices.add(new ConcertService(
                        resultSet.getInt("idConcertService"),
                        resultSet.getString("serviceName"),
                        resultSet.getString("type")
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return concertServices;
    }

    @Override
    public ArrayList<ConcertService> getConcertServiceByConcertId(int id) {
        LOGGER.info(String.format("Getting all concert services by concert id: %d", id));
        ArrayList<ConcertService> concertServices = new ArrayList<>();
        try {
            String query = "select * from concert_has_ConcertService as c " +
                    "inner join concertService as cs on c.ConcertService_idConcertService = cs.idConcertService " +
                    "where Concert_idConcert = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                concertServices.add(new ConcertService(
                        resultSet.getInt("idConcertService"),
                        resultSet.getString("serviceName"),
                        resultSet.getString("type")
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return concertServices;
    }
}
