package com.solvd.musichall.dao;

import com.solvd.musichall.models.services.CleanService;

import java.util.List;

public interface ICleanServiceDAO extends IBaseDAO<CleanService> {
    List<CleanService> getCleanServicesByScenarioID(int id);
}
