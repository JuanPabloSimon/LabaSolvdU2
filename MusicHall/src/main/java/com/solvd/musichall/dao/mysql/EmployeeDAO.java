package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.IEmployeeDAO;
import com.solvd.musichall.models.musicHall.MusicHall;
import com.solvd.musichall.models.people.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends MySQLDAO implements IEmployeeDAO {
    private static final Logger LOGGER = LogManager.getLogger(EmployeeDAO.class);

    @Override
    public Employee getByID(int id) {
        LOGGER.info(String.format("Searching Employee with id: %d", id));
        Employee em = null;
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_BY_ID = "SELECT * FROM employees " +
                    "INNER JOIN person ON employees.Person_idPerson = Person.idPerson " +
                    "WHERE employees.idEmployees= ?";
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                em = new Employee(
                        resultSet.getInt("idEmployees"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("idPerson"),
                        resultSet.getString("role"),
                        resultSet.getInt("age")
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return em;
    }

    @Override
    public Employee create(Employee employee) {
        return null;
    }

    public Employee create(Employee employee, MusicHall musicHall) {
        LOGGER.info("Creating Employee");
        try (Connection connection = MySQLDAO.getConnection();) {
            String CREATE = "INSERT INTO employees (role, Person_idPerson, MusicHall_idMusicHall) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, employee.getRole());
            statement.setInt(2, employee.getId());
            statement.setInt(3, musicHall.getId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                employee.setEmployeeID(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOGGER.info(String.format("Updating Employee with id: %d", employee.getEmployeeID()));
        try (Connection connection = MySQLDAO.getConnection();) {
            String UPDATE = "UPDATE Employees SET role = ?, Person_idPerson = ? WHERE idEmployees = ?";
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, employee.getRole());
            statement.setInt(2, employee.getId());
            statement.setInt(3, employee.getEmployeeID());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return employee;
    }

    @Override
    public void deleteByID(int id) {
        LOGGER.info(String.format("Deleting employee with id: %d", id));
        try (Connection connection = MySQLDAO.getConnection();) {
            String DELETE_BY_ID = "DELETE FROM employees WHERE idEmployees= ?";
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Employee> getAll() {
        LOGGER.info("Getting all employees");
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_ALL = "SELECT * FROM employees INNER JOIN person ON employees.Person_idPerson = person.idPerson ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getInt("idEmployees"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("idPerson"),
                        resultSet.getString("role"),
                        resultSet.getInt("age")
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return employees;
    }

    @Override
    public ArrayList<Employee> getEmployeeByMusicHallId(int id) {
        LOGGER.info(String.format("Searching Employee by MusicHallID: %d", id));
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_BY_MUSICHALL_ID = "SELECT * FROM employees INNER JOIN person ON employees.Person_idPerson = person.idPerson WHERE MusicHall_idMusicHall = =";
            PreparedStatement statement = connection.prepareStatement(GET_BY_MUSICHALL_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getInt("idEmployees"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("idPerson"),
                        resultSet.getString("role"),
                        resultSet.getInt("age")
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return employees;
    }
}
