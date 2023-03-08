package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.IBaseDAO;
import com.solvd.musichall.models.musicHall.MusicHall;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class MusicHallDAO extends MySQLDAO implements IBaseDAO<MusicHall> {
    private static final Logger LOGGER = LogManager.getLogger(MusicHall.class);

    @Override
    public MusicHall getByID(int id) {
        LOGGER.info(String.format("Searching MusicHall with id: %d", id));
        MusicHall m = null;
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_BY_ID = "SELECT * FROM MusicHall WHERE idMusicHall=?";
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                m = new MusicHall(
                        resultSet.getInt("idMusicHall"),
                        resultSet.getString("name"),
                        resultSet.getInt("scenariosAmount")
                );
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
        return m;
    }

    @Override
    public MusicHall create(MusicHall musicHall) {
        LOGGER.info(String.format("Creating MusicHall, id: %d.", musicHall.getId()));
        try (Connection connection = MySQLDAO.getConnection();) {
            String CREATE = "INSERT INTO MusicHall (name, scenariosAmount) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, musicHall.getName());
            statement.setInt(2, musicHall.getScenariosAmount());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                musicHall.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return musicHall;
    }

    @Override
    public MusicHall update(MusicHall musicHall) {
        LOGGER.info(String.format("Updating MusicHall with id: %d.", musicHall.getId()));
        try (Connection connection = MySQLDAO.getConnection();) {
            String UPDATE = "UPDATE MusicHall SET name = ?, scenariosAmount=?, WHERE idMusicHall= ?";
            PreparedStatement statement = connection.prepareStatement(UPDATE);

            statement.setString(1, musicHall.getName());
            statement.setInt(2, musicHall.getScenariosAmount());
            statement.setInt(3, musicHall.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return musicHall;
    }

    @Override
    public void deleteByID(int id) {
        LOGGER.info(String.format("Deleting MusicHall with id: %d", id));
        try (Connection connection = MySQLDAO.getConnection();) {
            String DELETE_BY_ID = "DELETE FROM MusicHall WHERE idMusicHall= ?";
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public ArrayList<MusicHall> getAll() {
        LOGGER.info("Finding all MusicHalls.");
        ArrayList<MusicHall> musicHalls = new ArrayList<>();
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_ALL = "SELECT * FROM MusicHall";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next())
                musicHalls.add(new MusicHall(
                        resultSet.getInt("idMusicHall"),
                        resultSet.getString("name"),
                        resultSet.getInt("scenariosAmount")
                ));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return musicHalls;
    }
}
