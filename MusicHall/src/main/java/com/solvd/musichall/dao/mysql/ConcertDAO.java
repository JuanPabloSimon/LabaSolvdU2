package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.IConcertDAO;
import com.solvd.musichall.models.event.Concert;

import java.util.ArrayList;
import java.util.List;

public class ConcertDAO extends MySQLDAO implements IConcertDAO {
    @Override
    public Concert getByID(int id) {
        return null;
    }

    @Override
    public Concert create(Concert concert) {
        return null;
    }

    @Override
    public Concert update(Concert concert) {
        return null;
    }

    @Override
    public void deleteByID(int id) {

    }

    @Override
    public List<Concert> getAll() {
        return null;
    }

    @Override
    public ArrayList<Concert> getConcertsByScenarioID(int id) {
        return null;
    }
}
