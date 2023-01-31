package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.IBandDAO;
import com.solvd.musichall.models.event.Band;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class BandDAO extends MySQLDAO implements IBandDAO {
    private static final Logger LOGGER = LogManager.getLogger(BandDAO.class);
    private final Connection connection;

    public BandDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Band getByID(int id) {
        LOGGER.info(String.format("Searching Band with id: %d", id));
        Band b = null;
        try {
            String query = "select * from bands where idBands = ?";
            PreparedStatement statement = connection.prepareStatement(query);

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
        LOGGER.info(String.format("Creating Band, id: %d", band.getBandID()));
        try {
            String query = "insert into bands (name, membersAmount, genre) values (?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(query);
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
        try {
            String query = "update bands set name = ?, membersAmouny = ?, genre = ? where idBands = ?";
            PreparedStatement statement = connection.prepareStatement(query);
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
        try {
            String query = "Delete from bands where idBands = ?";
            PreparedStatement statement = connection.prepareStatement(query);
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
        try {
            String query = "Select * from bands";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
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
