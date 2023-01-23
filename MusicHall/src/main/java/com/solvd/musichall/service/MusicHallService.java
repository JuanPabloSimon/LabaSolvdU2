package com.solvd.musichall.service;

import com.solvd.musichall.dao.mysql.EmployeeDAO;
import com.solvd.musichall.dao.mysql.MusicHallDAO;
import com.solvd.musichall.models.musicHall.MusicHall;
import com.solvd.musichall.models.musicHall.Scenario;
import com.solvd.musichall.models.people.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;

public class MusicHallService {
    private final static Logger LOGGER = LogManager.getLogger(MusicHallService.class);
    private final Connection connection;
    private MusicHallDAO mDAO;
    private ScenarioService sService;
    private EmployeeDAO eDAO;

    public MusicHallService(Connection connection) {
        this.connection = connection;
        this.mDAO = new MusicHallDAO(connection);
        this.sService = new ScenarioService(connection);
        this.eDAO = new EmployeeDAO(connection);
    }

    public MusicHall getMusicHallById(int id) {
        MusicHall musicHall = mDAO.getByID(id);
        ArrayList<Scenario> scenarios = sService.getAllScenariosByMusicHallId(id);
        for (Scenario scenario : scenarios) {
            musicHall.addScenario(scenario);
        }
        ArrayList<Employee> employees = eDAO.getEmployeeByMusicHallId(id);
        for (Employee employee : employees) {
            musicHall.addEmployee(employee);
        }
        return musicHall;
    }

    public ArrayList<MusicHall> getAllMusicHall() {
        ArrayList<MusicHall> musicHalls = mDAO.getAll();
        for (MusicHall musicHall : musicHalls) {
            ArrayList<Scenario> scenarios = sService.getAllScenariosByMusicHallId(musicHall.getId());
            for (Scenario scenario : scenarios) {
                musicHall.addScenario(scenario);
            }
            ArrayList<Employee> employees = eDAO.getEmployeeByMusicHallId(musicHall.getId());
            for (Employee employee : employees) {
                musicHall.addEmployee(employee);
            }
        }
        return musicHalls;
    }

}
