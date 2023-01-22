package com.solvd.musichall.dao;

import com.solvd.musichall.models.musicHall.Scenario;

import java.util.List;

public interface IScenarioDAO extends IBaseDAO<Scenario> {
    List<Scenario> getScenariosByMusicHallID(int ID);
}
