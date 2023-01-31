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
    private final Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Employee getByID(int id) {
        LOGGER.info(String.format("Searching Employee with id: %d", id));
        Employee em = null;
        try {
            String query = "select * from employees \n" +
                    "inner Join person on employees.Person_idPerson = Person.idPerson \n" +
                    "where employees.idEmployees= ?";
            PreparedStatement statement = connection.prepareStatement(query);
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
        try {
            String query = "Insert into employees (role, Person_idPerson, MusicHall_idMusicHall) " +
                    "values (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

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
        try {
            String query = "Update Employees set role = ?, Person_idPerson = ? where idEmployees = ?";
            PreparedStatement statement = connection.prepareStatement(query);
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
        try {
            String query = "Delete from employees where idEmployees= ?";
            PreparedStatement statement = connection.prepareStatement(query);
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
        try {
            String query = "select * from employees inner Join person on employees.Person_idPerson = person.idPerson ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

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
        try {
            String query = "select * from employees inner Join person on employees.Person_idPerson = person.idPerson where MusicHall_idMusicHall = =";
            PreparedStatement statement = connection.prepareStatement(query);
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
