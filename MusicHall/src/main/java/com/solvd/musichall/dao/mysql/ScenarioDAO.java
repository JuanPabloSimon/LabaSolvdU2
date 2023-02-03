package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.IScenarioDAO;
import com.solvd.musichall.models.musicHall.MusicHall;
import com.solvd.musichall.models.musicHall.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class ScenarioDAO extends MySQLDAO implements IScenarioDAO {
    private static final Logger LOGGER = LogManager.getLogger(ScenarioDAO.class);
    private final Connection connection;

    public ScenarioDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Scenario getByID(int id) {
        LOGGER.info(String.format("Searching Scenario with id: %d", id));
        Scenario s = null;
        try {
            String query = "SELECT * FROM scenarioRoom WHERE idScenario=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                s = new Scenario(
                        resultSet.getInt("idScenario"),
                        resultSet.getString("name"),
                        resultSet.getInt("capability")
                );
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
        return s;
    }

    @Override
    public Scenario create(Scenario scenario) {
        return null;
    }

    public Scenario create(Scenario scenario, MusicHall musichall) {
        LOGGER.info(String.format("Creating  a new Scenario named: %s", scenario.getName()));
        try {
            String query = "INSERT INTO ScenarioRoom (name, capability, MusicHall_idMusicHall) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, scenario.getName());
            statement.setInt(2, scenario.getCapability());
            statement.setInt(3, musichall.getId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                scenario.setScenarioID(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return scenario;
    }

    @Override
    public Scenario update(Scenario scenario) {
        LOGGER.info(String.format("Updating Scenario with id: %d", scenario.getScenarioID()));

        try {
            String query = "UPDATE ScenarioRoom SET name= ?, capability = ?, WHERE idScenario= ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, scenario.getName());
            statement.setInt(2, scenario.getCapability());
            statement.setInt(3, scenario.getScenarioID());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return scenario;
    }

    @Override
    public void deleteByID(int id) {
        LOGGER.info(String.format("Deleting scenario with id: %d", id));

        try {
            String query = "DELETE FROM ScenarioRoom WHERE idPerson= =";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Override
    public ArrayList<Scenario> getAll() {
        LOGGER.info("Find ALl Scenarios");
        ArrayList<Scenario> scenarios = new ArrayList<>();

        try {
            String query = "SELECT * FROM ScenarioRoom";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                scenarios.add(new Scenario(
                        resultSet.getInt("idScenario"),
                        resultSet.getString("name"),
                        resultSet.getInt("capability")
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return scenarios;
    }

    @Override
    public ArrayList<Scenario> getScenariosByMusicHallID(int id) {
        LOGGER.info(String.format("Searching Scenario by MusicHallID: %d", id));
        ArrayList<Scenario> scenarios = new ArrayList<>();
        try {
            String query = "SELECT * FROM ScenarioRoom where MusicHall_idMusicHall = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                scenarios.add(new Scenario(
                        resultSet.getInt("idScenario"),
                        resultSet.getString("name"),
                        resultSet.getInt("capability")
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return scenarios;
    }
}
