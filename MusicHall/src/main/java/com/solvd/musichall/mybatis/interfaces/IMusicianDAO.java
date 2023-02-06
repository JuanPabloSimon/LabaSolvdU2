package com.solvd.musichall.mybatis.interfaces;

import com.solvd.musichall.models.people.Musician;

import java.util.List;

public interface IMusicianDAO {
    Musician getByID(int id);

    void create(Musician m);

    void update(Musician m);

    void deleteByID(int id);

    List<Musician> getAll();

    List<Musician> getMusiciansByBandId(int id);
}
