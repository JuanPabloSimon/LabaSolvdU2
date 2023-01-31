package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.ICleanServiceDAO;
import com.solvd.musichall.models.services.CleanService;

import java.util.ArrayList;
import java.util.List;

public class CleanServiceDAO extends MySQLDAO implements ICleanServiceDAO {
    @Override
    public CleanService getByID(int id) {

        return null;
    }

    @Override
    public CleanService create(CleanService cleanService) {
        return null;
    }

    @Override
    public CleanService update(CleanService cleanService) {
        return null;
    }

    @Override
    public void deleteByID(int id) {

    }

    @Override
    public List<CleanService> getAll() {
        return null;
    }

    @Override
    public ArrayList<CleanService> getCleanServicesByScenarioID(int id) {
        return null;
    }
}
