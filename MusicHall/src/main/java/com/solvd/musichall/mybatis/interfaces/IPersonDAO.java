package com.solvd.musichall.mybatis.interfaces;

import com.solvd.musichall.models.people.Person;

import java.util.List;

public interface IPersonDAO {

    Person getByID(int id);

    void create(Person p);

    void update(Person p);

    void deleteByID(int id);

    List<Person> getAll();
}
