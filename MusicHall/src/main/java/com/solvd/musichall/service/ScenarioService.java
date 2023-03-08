package com.solvd.musichall.service;

import com.solvd.musichall.dao.mysql.CleanServiceDAO;
import com.solvd.musichall.dao.mysql.MusicHallDAO;
import com.solvd.musichall.dao.mysql.ScenarioDAO;
import com.solvd.musichall.dao.mysql.SeatsDAO;
import com.solvd.musichall.models.event.Concert;
import com.solvd.musichall.models.musicHall.Scenario;
import com.solvd.musichall.models.musicHall.Seat;
import com.solvd.musichall.models.services.CleanService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ScenarioService {
    private static final Logger LOGGER = LogManager.getLogger(ScenarioService.class);
    private ScenarioDAO sDAO;
    private MusicHallDAO mDAO;
    private ConcertService cService;
    private SeatsDAO seatDAO;
    private CleanServiceDAO cleanDAO;


    public ScenarioService() {
        this.sDAO = new ScenarioDAO();
        this.mDAO = new MusicHallDAO();
        this.cService = new ConcertService();
        this.seatDAO = new SeatsDAO();
        this.cleanDAO = new CleanServiceDAO();
    }

    public Scenario getScenarioById(int id) {
        Scenario scenario = sDAO.getByID(id);
        ArrayList<Concert> concerts = cService.getConcertByScenarioId(id);
        for (Concert concert : concerts) {
            scenario.addConcert(concert);
        }
        ArrayList<Seat> seats = seatDAO.getSeatByScenarioId(id);
        for (Seat seat : seats) {
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
            ArrayList<Concert> concerts = cService.getConcertByScenarioId(scenario.getScenarioID());
            for (Concert concert : concerts) {
                scenario.addConcert(concert);
            }
            ArrayList<Seat> seats = seatDAO.getSeatByScenarioId(scenario.getScenarioID());
            for (Seat seat : seats) {
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
            ArrayList<Concert> concerts = cService.getConcertByScenarioId(scenario.getScenarioID());
            for (Concert concert : concerts) {
                scenario.addConcert(concert);
            }
            ArrayList<Seat> seats = seatDAO.getSeatByScenarioId(scenario.getScenarioID());
            for (Seat seat : seats) {
                scenario.addSeats(seat);
            }
            ArrayList<CleanService> cleanServices = cleanDAO.getCleanServicesByScenarioID(scenario.getScenarioID());
            for (CleanService cleanService : cleanServices) {
                scenario.addCleanService(cleanService);
            }
        }
        return scenarios;
    }


}
