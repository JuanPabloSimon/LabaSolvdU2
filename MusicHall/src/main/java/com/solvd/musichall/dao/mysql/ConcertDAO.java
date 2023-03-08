package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.IConcertDAO;
import com.solvd.musichall.models.event.Band;
import com.solvd.musichall.models.event.Concert;
import com.solvd.musichall.models.musicHall.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConcertDAO extends MySQLDAO implements IConcertDAO {
    private static final Logger LOGGER = LogManager.getLogger(ConcertDAO.class);

    @Override
    public Concert getByID(int id) {
        LOGGER.info(String.format("Searching Concert with id: %d", id));
        Concert c = null;
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_BY_ID = "SELECT * FROM concert AS c INNER JOIN bands AS b ON c.Bands_idBands = b.idBands WHERE idConcert = ?";
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                c = new Concert(
                        resultSet.getInt("idConcert"),
                        resultSet.getFloat("durationMin"),
                        resultSet.getDate("date"),
                        new Band(
                                resultSet.getInt("idBands"),
                                resultSet.getString("name"),
                                resultSet.getString("genre"),
                                resultSet.getInt("membersAmount")
                        )
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return c;
    }

    @Override
    public Concert create(Concert concert) {
        return null;
    }

    public Concert create(Concert concert, Scenario scenario) {
        LOGGER.info(String.format("Creating  a new Concert of: %s", concert.getBand().getName()));
        try (Connection connection = MySQLDAO.getConnection();) {
            String CREATE = "INSERT INTO concert (durationMin,Bands_idBands, ScenarioRoom_idScenario, date) VALUES " +
                    "(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setFloat(1, concert.getDuration());
            statement.setInt(2, concert.getBand().getBandID());
            statement.setInt(3, scenario.getScenarioID());
            statement.setDate(4, new java.sql.Date(concert.getDate().getTime()));
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                concert.setConcertID(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return concert;
    }

    @Override
    public Concert update(Concert concert) {
        LOGGER.info(String.format("Updating Concert with id: %d", concert.getConcertID()));

        try (Connection connection = MySQLDAO.getConnection();) {
            String UPDATE = "UPDATE Concert SET durationMin= ?, Bands_idBands, WHERE idConcert= ?";
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setFloat(1, concert.getDuration());
            statement.setInt(2, concert.getBand().getBandID());
            statement.setInt(3, concert.getConcertID());

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return concert;
    }

    @Override
    public void deleteByID(int id) {
        LOGGER.info(String.format("Deleting concert with id: %d", id));

        try (Connection connection = MySQLDAO.getConnection();) {
            String DELETE_BY_ID = "DELETE FROM concert WHERE idConcert=?";
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Concert> getAll() {
        LOGGER.info("Find all Concerts");
        ArrayList<Concert> concerts = new ArrayList<>();
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_ALL = "SELECT * FROM concert AS c INNER JOIN bands AS b ON c.Bands_idBands = b.idBands";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                concerts.add(new Concert(
                        resultSet.getInt("idConcert"),
                        resultSet.getFloat("durationMin"),
                        resultSet.getDate("date"),
                        new Band(
                                resultSet.getInt("idBands"),
                                resultSet.getString("name"),
                                resultSet.getString("genre"),
                                resultSet.getInt("membersAmount")
                        )
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return concerts;
    }

    @Override
    public ArrayList<Concert> getConcertsByScenarioID(int id) {
        LOGGER.info(String.format("Searching Concerts by Scenario id: %d", id));
        ArrayList<Concert> concerts = new ArrayList<>();
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_BY_SCENARIO_ID = "SELECT * FROM concert AS c INNER JOIN bands AS b ON c.Bands_idBands = b.idBands WHERE ScenarioRoom_idScenario = ?";
            PreparedStatement statement = connection.prepareStatement(GET_BY_SCENARIO_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                concerts.add(new Concert(
                        resultSet.getInt("idConcert"),
                        resultSet.getFloat("durationMin"),
                        resultSet.getDate("date"),
                        new Band(
                                resultSet.getInt("idBands"),
                                resultSet.getString("name"),
                                resultSet.getString("genre"),
                                resultSet.getInt("membersAmount")
                        )
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return concerts;
    }
}
