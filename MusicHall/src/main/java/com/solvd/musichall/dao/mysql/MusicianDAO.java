package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.IMusicianDAO;
import com.solvd.musichall.models.event.Band;
import com.solvd.musichall.models.people.Musician;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class MusicianDAO extends MySQLDAO implements IMusicianDAO {
    private static final Logger LOGGER = LogManager.getLogger(MusicianDAO.class);

    @Override
    public Musician getByID(int id) {
        LOGGER.info(String.format("Searching Musician with id: %d", id));
        Musician m = null;
        try (Connection connection = MySQLDAO.getConnection()) {
            String GET_BY_ID = "SELECT * FROM musician " +
                    "INNER JOIN person ON musician.Person_idPerson = person.idPerson " +
                    "WHERE idMusician = ?";
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                m = new Musician(
                        resultSet.getInt("idMusician"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("age"),
                        resultSet.getInt("idPerson"),
                        resultSet.getString("role")
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return m;
    }

    @Override
    public Musician create(Musician musician) {
        return null;
    }

    public Musician create(Musician musician, Band band) {
        LOGGER.info("Creating musician");
        try (Connection connection = MySQLDAO.getConnection()) {
            String CREATE = "INSERT INTO musician (Person_idPerson, Band_idBand, role) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, musician.getId());
            statement.setInt(2, band.getBandID());
            statement.setString(2, musician.getRole());
            statement.executeUpdate();

            ResultSet resultset = statement.getGeneratedKeys();
            while (resultset.next()) {
                musician.setMusicianID(resultset.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return musician;
    }

    @Override
    public Musician update(Musician musician) {
        LOGGER.info(String.format("Updating Employee with id: %d", musician.getMusicianId()));
        try (Connection connection = MySQLDAO.getConnection()) {
            String UPDATE = "UPDATE musician SET role= ?, Person_idPerson = ? WHERE idMusician = ?";
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, musician.getRole());
            statement.setInt(2, musician.getId());
            statement.setInt(3, musician.getMusicianId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return musician;
    }

    @Override
    public void deleteByID(int id) {
        LOGGER.info(String.format("Deleting musician with id: %d", id));
        try (Connection connection = MySQLDAO.getConnection()) {
            String DELETE_BY_ID = "DELETE FROM musician WHERE idMusician = ?";
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public ArrayList<Musician> getAll() {
        LOGGER.info("Getting all musicians");
        ArrayList<Musician> musicians = new ArrayList<Musician>();
        try (Connection connection = MySQLDAO.getConnection()) {
            String GET_ALL = "SELECT * FROM musician";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                musicians.add(new Musician(
                        resultSet.getInt("idMusician"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("age"),
                        resultSet.getInt("idPerson"),
                        resultSet.getString("role")
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return musicians;
    }

    @Override
    public ArrayList<Musician> getMusiciansByBandId(int id) {
        LOGGER.info(String.format("Searching Musician by BandId: %d", id));
        ArrayList<Musician> musicians = new ArrayList<>();
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_BY_MUSICIAN_ID = "SELECT * FROM musician " +
                    "INNER JOIN person ON musician.Person_idPerson = person.idPerson " +
                    "WHERE Bands_idBands = ?";
            PreparedStatement statement = connection.prepareStatement(GET_BY_MUSICIAN_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                musicians.add(new Musician(
                        resultSet.getInt("idMusician"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("age"),
                        resultSet.getInt("idPerson"),
                        resultSet.getString("role")
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return musicians;
    }
}
