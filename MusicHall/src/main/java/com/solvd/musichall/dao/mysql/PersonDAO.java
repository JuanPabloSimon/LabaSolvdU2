package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.IPersonDAO;
import com.solvd.musichall.models.people.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersonDAO extends MySQLDAO implements IPersonDAO {
    private static final Logger LOGGER = LogManager.getLogger(PersonDAO.class);
    private final Connection connection;

    public PersonDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Person getByID(int id) {
        LOGGER.info("Searching Person with id: " + id);
        try {
            String q = "SELECT * FROM Person WHERE idPerson=?";
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return new Person(
                    resultSet.getString("name"),
                    resultSet.getString("lastName"),
                    resultSet.getInt("age")
            );
        } catch (SQLException sqle) {
            LOGGER.error(sqle.getMessage(), sqle);
        }
        return null;
    }

    @Override
    public Person create(Person person) {
        LOGGER.info("Creating Person, id: " + person.getId());
        try {
            String q = "INSERT INTO Person (name, lastname, age) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(q);

            statement.setString(1, person.getName());
            statement.setString(2, person.getLastname());
            statement.setInt(3, person.getAge());
            statement.executeUpdate();
        } catch (SQLException sqle) {
            LOGGER.error(sqle.getMessage(), sqle);
        }
        return person;
    }

    @Override
    public Person update(Person person) {
        LOGGER.info("Updating person with id " + person.getId() + ".");
        try {
            String q = "UPDATE Persons SET name = ?, lasName=?, age = ?, WHERE idPerson= ?";
            PreparedStatement statement = connection.prepareStatement(q);

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
        LOGGER.info("Deleting person with id " + id + ".");
        try {
            String query = "DELETE FROM Persons WHERE idPerson= ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Person> getAll() {
        LOGGER.info("Finding all Persons.");
        List<Person> persons = new ArrayList<>();
        try {
            String query = "SELECT * FROM person";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
                persons.add(new Person(
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("idPerson"),
                        resultSet.getInt("age")
                ));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return persons;
    }
}
