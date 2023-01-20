package com.solvd.musichall.dao;

import java.util.List;

public interface IBaseDAO<T> {

    T getByID(int id);

    T create(T t);

    T update(T t);

    void deleteByID(int id);

    List<T> getAll();
}
