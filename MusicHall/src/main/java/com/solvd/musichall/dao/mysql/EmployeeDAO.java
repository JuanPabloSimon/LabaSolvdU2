package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.IEmployeeDAO;
import com.solvd.musichall.models.people.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        try {
            String query = "select * from employees \n" +
                    "inner Join Person on employees.Person_idPerson = Person.idPerson \n" +
                    "where employees.idEmployees= ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return new Employee(
                    resultSet.getInt("idEmployees"),
                    resultSet.getString("name"),
                    resultSet.getString("lastName"),
                    resultSet.getInt("idPerson"),
                    resultSet.getString("role")
            );
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Employee create(Employee employee) {
        return null;
    }

    @Override
    public Employee update(Employee employee) {
        return null;
    }

    @Override
    public void deleteByID(int id) {

    }

    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public ArrayList<Employee> getEmployeeByMusicHallId(int id) {
        return null;
    }
}
