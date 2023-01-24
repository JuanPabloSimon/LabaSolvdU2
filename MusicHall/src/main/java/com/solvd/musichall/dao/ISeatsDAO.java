package com.solvd.musichall.dao;

import com.solvd.musichall.models.musicHall.Seat;

import java.util.List;

public interface ISeatsDAO extends IBaseDAO<Seat> {
    List<Seat> getSeatByScenarioId(int id);
}
