package com.solvd.musichall.dao;

import com.solvd.musichall.models.event.Concert;

import java.util.List;

public interface IConcertDAO extends IBaseDAO<Concert> {
    List<Concert> getConcertsByScenarioID(int id);
}
