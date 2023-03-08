package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.IPersonDAO;
import com.solvd.musichall.models.people.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class PersonDAO extends MySQLDAO implements IPersonDAO {
    private static final Logger LOGGER = LogManager.getLogger(PersonDAO.class);

    @Override
    public Person getByID(int id) {
        LOGGER.info(String.format("Searching Person with id: %d", id));
        Person p = null;
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_BY_ID = "SELECT * FROM person WHERE idPerson = ?";
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                p = new Person(
                        resultSet.getInt("idPerson"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("age")
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return p;
    }


    @Override
    public Person create(Person person) {
        LOGGER.info(String.format("Creating Person, id: %d", person.getId()));
        try (Connection connection = MySQLDAO.getConnection();) {
            String CREATE = "INSERT INTO person (name, lastname, age) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, person.getName());
            statement.setString(2, person.getLastname());
            statement.setInt(3, person.getAge());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                person.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return person;
    }

    @Override
    public Person update(Person person) {
        LOGGER.info(String.format("Updating person with id: %d", person.getId()));
        try (Connection connection = MySQLDAO.getConnection();) {
            String UPDATE = "UPDATE person SET name = ?, lastName=?, age = ?, WHERE idPerson= ?";
            PreparedStatement statement = connection.prepareStatement(UPDATE);

            statement.setString(1, person.getName());
            statement.setString(2, person.getLastname());
            statement.setInt(3, person.getAge());
            statement.setInt(4, person.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return person;
    }

    @Override
    public void deleteByID(int id) {
        LOGGER.info(String.format("Deleting person with id: %d.", id));
        try (Connection connection = MySQLDAO.getConnection();) {
            String DELETE_BY_ID = "DELETE FROM persons WHERE idPerson= ?";
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public ArrayList<Person> getAll() {
        LOGGER.info("Finding all Persons.");
        ArrayList<Person> persons = new ArrayList<>();
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_ALL = "SELECT * FROM person";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next())
                persons.add(new Person(
                        resultSet.getInt("idPerson"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("age")
                ));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return persons;
    }
}
