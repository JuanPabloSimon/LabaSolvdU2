package com.solvd.musichall.dao;

import java.util.List;

public interface IBaseDAO<T> {

    T get(int id);

    T create(T t);

    T update(T t);

    void delete(int id);

    List<T> getAll();
}
