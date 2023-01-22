package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.ISeatsDAO;
import com.solvd.musichall.models.musicHall.Seats;

import java.util.ArrayList;
import java.util.List;

public class SeatsDAO extends MySQLDAO implements ISeatsDAO {
    @Override
    public Seats getByID(int id) {
        return null;
    }

    @Override
    public Seats create(Seats seats) {
        return null;
    }

    @Override
    public Seats update(Seats seats) {
        return null;
    }

    @Override
    public void deleteByID(int id) {

    }

    @Override
    public List<Seats> getAll() {
        return null;
    }

    @Override
    public ArrayList<Seats> getSeatByScenarioId(int id) {
        return null;
    }
}
