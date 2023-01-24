package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.ISeatsDAO;
import com.solvd.musichall.models.musicHall.Seat;

import java.util.ArrayList;
import java.util.List;

public class SeatsDAO extends MySQLDAO implements ISeatsDAO {
    @Override
    public Seat getByID(int id) {
        return null;
    }

    @Override
    public Seat create(Seat seat) {
        return null;
    }

    @Override
    public Seat update(Seat seat) {
        return null;
    }

    @Override
    public void deleteByID(int id) {

    }

    @Override
    public List<Seat> getAll() {
        return null;
    }

    @Override
    public ArrayList<Seat> getSeatByScenarioId(int id) {
        return null;
    }
}
