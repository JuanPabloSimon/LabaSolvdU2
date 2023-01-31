package com.solvd.musichall.dao;

import com.solvd.musichall.models.people.Musician;

import java.util.List;

public interface IMusicianDAO extends IBaseDAO<Musician> {
    List<Musician> getMusiciansbyBandId(int id);
}
