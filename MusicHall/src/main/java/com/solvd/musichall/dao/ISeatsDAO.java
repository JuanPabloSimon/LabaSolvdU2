package com.solvd.musichall.dao;

import com.solvd.musichall.models.musicHall.Seats;

import java.util.List;

public interface ISeatsDAO extends IBaseDAO<Seats> {
    List<Seats> getSeatByScenarioId(int id);
}
