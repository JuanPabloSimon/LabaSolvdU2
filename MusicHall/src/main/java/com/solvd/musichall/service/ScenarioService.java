package com.solvd.musichall.service;

import com.solvd.musichall.dao.mysql.*;
import com.solvd.musichall.models.event.Concert;
import com.solvd.musichall.models.musicHall.Scenario;
import com.solvd.musichall.models.musicHall.Seats;
import com.solvd.musichall.models.services.CleanService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;

public class ScenarioService {
    private static final Logger LOGGER = LogManager.getLogger(ScenarioService.class);
    private final Connection connection;
    private ScenarioDAO sDAO;
    private MusicHallDAO mDAO;
    private ConcertDAO cDAO;
    private SeatsDAO seatDAO;
    private CleanServiceDAO cleanDAO;


    public ScenarioService(Connection connection) {
        this.connection = connection;
        this.sDAO = new ScenarioDAO(connection);
        this.mDAO = new MusicHallDAO(connection);
        this.cDAO = new ConcertDAO();
        this.seatDAO = new SeatsDAO();
        this.cleanDAO = new CleanServiceDAO();
    }

    public Scenario getScenarioById(int id) {
        Scenario scenario = sDAO.getByID(id);
        ArrayList<Concert> concerts = cDAO.getConcertsByScenarioID(id);
        for (Concert concert : concerts) {
            scenario.addConcert(concert);
        }
        ArrayList<Seats> seats = seatDAO.getSeatByScenarioId(id);
        for (Seats seat : seats) {
            scenario.addSeats(seat);
        }
        ArrayList<CleanService> cleanServices = cleanDAO.getCleanServicesByScenarioID(id);
        for (CleanService cleanService : cleanServices) {
            scenario.addCleanService(cleanService);
        }
        return scenario;
    }

    public Scenario createScenario(Scenario scenario) {
        return null;
    }

    public ArrayList<Scenario> getAllScenarios() {
        ArrayList<Scenario> scenarios = sDAO.getAll();
        for (Scenario scenario : scenarios) {
            ArrayList<Concert> concerts = cDAO.getConcertsByScenarioID(scenario.getScenarioID());
            for (Concert concert : concerts) {
                scenario.addConcert(concert);
            }
            ArrayList<Seats> seats = seatDAO.getSeatByScenarioId(scenario.getScenarioID());
            for (Seats seat : seats) {
                scenario.addSeats(seat);
            }
            ArrayList<CleanService> cleanServices = cleanDAO.getCleanServicesByScenarioID(scenario.getScenarioID());
            for (CleanService cleanService : cleanServices) {
                scenario.addCleanService(cleanService);
            }
        }
        return scenarios;
    }

    public ArrayList<Scenario> getAllScenariosByMusicHallId(int id) {
        ArrayList<Scenario> scenarios = sDAO.getScenariosByMusicHallID(id);
        for (Scenario scenario : scenarios) {
            ArrayList<Concert> concerts = cDAO.getConcertsByScenarioID(id);
            for (Concert concert : concerts) {
                scenario.addConcert(concert);
            }
            ArrayList<Seats> seats = seatDAO.getSeatByScenarioId(id);
            for (Seats seat : seats) {
                scenario.addSeats(seat);
            }
            ArrayList<CleanService> cleanServices = cleanDAO.getCleanServicesByScenarioID(id);
            for (CleanService cleanService : cleanServices) {
                scenario.addCleanService(cleanService);
            }
        }
        return scenarios;
    }


}
