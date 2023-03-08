package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.IBandDAO;
import com.solvd.musichall.models.event.Band;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class BandDAO extends MySQLDAO implements IBandDAO {
    private static final Logger LOGGER = LogManager.getLogger(BandDAO.class);

    @Override
    public Band getByID(int id) {
        LOGGER.info(String.format("Searching Band with id: %d", id));
        Band b = null;
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_BY_ID = "SELECT * FROM bands WHERE idBands = ?";
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                b = new Band(
                        resultSet.getInt("idBands"),
                        resultSet.getString("name"),
                        resultSet.getString("genre"),
                        resultSet.getInt("membersAmount")
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return b;
    }

    @Override
    public Band create(Band band) {
        LOGGER.info(String.format("Creating Band, name: %s", band.getName()));
        try (Connection connection = MySQLDAO.getConnection()) {
            String CREATE = "INSERT INTO bands (name, membersAmount, genre) VALUES (?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, band.getName());
            statement.setInt(2, band.getMembersAmount());
            statement.setString(3, band.getGenre());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                band.setID(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return band;
    }

    @Override
    public Band update(Band band) {
        LOGGER.info(String.format("Updating Employee with id: %d", band.getBandID()));
        try (Connection connection = MySQLDAO.getConnection()) {
            String UPDATE = "UPDATE bands SET name = ?, membersAmouny = ?, genre = ? WHERE idBands = ?";
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, band.getName());
            statement.setInt(2, band.getMembersAmount());
            statement.setString(3, band.getGenre());
            statement.setInt(4, band.getBandID());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return band;
    }

    @Override
    public void deleteByID(int id) {
        LOGGER.info(String.format("Deleting band with id: %d", id));
        try (Connection connection = MySQLDAO.getConnection()) {
            String DELETE_BY_ID = "DELETE FROM bands WHERE idBands = ?";
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public ArrayList<Band> getAll() {
        LOGGER.info("Getting all bands");
        ArrayList<Band> bands = new ArrayList<Band>();
        try (Connection connection = MySQLDAO.getConnection()) {
            String GET_ALL = "SELECT * FROM bands";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                bands.add(new Band(
                        resultSet.getInt("idBands"),
                        resultSet.getString("name"),
                        resultSet.getString("genre"),
                        resultSet.getInt("membersAmount")
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return bands;
    }
}
