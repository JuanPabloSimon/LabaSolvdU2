package com.solvd.musichall.service;

import com.solvd.musichall.dao.mysql.EmployeeDAO;
import com.solvd.musichall.dao.mysql.MusicHallDAO;
import com.solvd.musichall.models.musicHall.MusicHall;
import com.solvd.musichall.models.musicHall.Scenario;
import com.solvd.musichall.models.people.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class MusicHallService {
    private final static Logger LOGGER = LogManager.getLogger(MusicHallService.class);
    private MusicHallDAO mDAO;
    private ScenarioService sService;
    private EmployeeDAO eDAO;

    public MusicHallService() {
        this.mDAO = new MusicHallDAO();
        this.sService = new ScenarioService();
        this.eDAO = new EmployeeDAO();
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

    public MusicHall createMusicHall(MusicHall musicHall) {
        MusicHall m = mDAO.create(musicHall);
        ArrayList<Employee> employees = musicHall.getEmployees();
        for (Employee e : employees) {
            eDAO.create(e);
        }
        ArrayList<Scenario> scenarios = musicHall.getScenarios();
        for (Scenario s : scenarios) {
            sService.createScenario(s);
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
