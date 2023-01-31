package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.ISeatsDAO;
import com.solvd.musichall.models.musicHall.Scenario;
import com.solvd.musichall.models.musicHall.Seat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class SeatsDAO extends MySQLDAO implements ISeatsDAO {
    private static final Logger LOGGER = LogManager.getLogger(SeatsDAO.class);
    private final Connection connection;

    public SeatsDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Seat getByID(int id) {
        LOGGER.info(String.format("Searching Seat with id: %d", id));
        Seat s = null;
        try {
            String query = "select * from seats where idSeats = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                s = new Seat(
                        resultSet.getInt("idSeats"),
                        resultSet.getInt("number"),
                        resultSet.getBoolean("reserved")
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return s;
    }

    @Override
    public Seat create(Seat seat) {
        return null;
    }

    public Seat create(Seat seat, Scenario scenario) {
        LOGGER.info("Creating seat");
        try {
            String query = "insert into seats (number, reserved, Scenario_idScenario) values (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, seat.getNumeration());
            statement.setBoolean(2, seat.isReserved());
            statement.setInt(2, scenario.getScenarioID());
            statement.executeUpdate();

            ResultSet resultset = statement.getGeneratedKeys();
            while (resultset.next()) {
                seat.setSeatID(resultset.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return seat;
    }

    @Override
    public Seat update(Seat seat) {
        LOGGER.info(String.format("Updating Seat with id: %d", seat.getSeatID()));
        try {
            String query = "update seats set number= ?, reserved = ? where idSeats = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, seat.getNumeration());
            statement.setBoolean(2, seat.isReserved());
            statement.setInt(3, seat.getSeatID());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return seat;
    }

    @Override
    public void deleteByID(int id) {
        LOGGER.info(String.format("Deleting seat with id: %d", id));
        try {
            String query = "delete from seats where idSeats = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public ArrayList<Seat> getAll() {
        LOGGER.info("Getting all seats");
        ArrayList<Seat> seats = new ArrayList<Seat>();
        try {
            String query = "select * from seats";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                seats.add(new Seat(
                        resultSet.getInt("idSeats"),
                        resultSet.getInt("number"),
                        resultSet.getBoolean("reserved")
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return seats;
    }

    @Override
    public ArrayList<Seat> getSeatByScenarioId(int id) {
        LOGGER.info(String.format("Searching Seats by ScenarioId: %d", id));
        ArrayList<Seat> seats = new ArrayList<>();
        try {
            String query = "select * from seats where Scenario_idScenario = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                seats.add(new Seat(
                        resultSet.getInt("idSeats"),
                        resultSet.getInt("number"),
                        resultSet.getBoolean("reserved")
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return seats;
    }
}
