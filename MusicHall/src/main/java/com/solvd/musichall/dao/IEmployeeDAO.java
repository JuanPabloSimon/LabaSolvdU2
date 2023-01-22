package com.solvd.musichall.dao;

import com.solvd.musichall.models.people.Employee;

import java.util.List;

public interface IEmployeeDAO extends IBaseDAO<Employee> {
    List<Employee> getEmployeeByMusicHallId(int id);
}
