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
    private final Connection connection;

    public MusicianDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Musician getByID(int id) {
        LOGGER.info(String.format("Searching Musician with id: %d", id));
        Musician m = null;
        try {
            String query = "select * from musician " +
                    "inner join person on musician.Person_idPerson = person.idPerson " +
                    "where idMusician = ?";
            PreparedStatement statement = connection.prepareStatement(query);
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
        try {
            String query = "insert into musician (Person_idPerson, Band_idBand, role) values (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
        try {
            String query = "update musician set role= ?, Person_idPerson = ? where idMusician = ?";
            PreparedStatement statement = connection.prepareStatement(query);
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
        try {
            String query = "delete from musician where idMusician = ?";
            PreparedStatement statement = connection.prepareStatement(query);
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
        try {
            String query = "select * from musician";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
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
        try {
            String query = "select * from musician " +
                    "inner join person on musician.Person_idPerson = person.idPerson " +
                    "where Bands_idBands = ?";
            PreparedStatement statement = connection.prepareStatement(query);
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
