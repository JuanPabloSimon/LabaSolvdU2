package com.solvd.musichall.mybatis.interfaces;

import com.solvd.musichall.models.event.Band;

import java.util.List;

public interface IBandDAO {

    Band getByID(int id);

    void create(Band b);

    void update(Band b);

    void deleteByID(int id);

    List<Band> getAll();
}
