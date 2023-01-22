package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.IEmployeeDAO;
import com.solvd.musichall.models.people.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends MySQLDAO implements IEmployeeDAO {
    @Override
    public Employee getByID(int id) {
        
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
