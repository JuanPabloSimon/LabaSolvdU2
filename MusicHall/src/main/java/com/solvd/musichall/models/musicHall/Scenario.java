package com.solvd.musichall.models.musicHall;

import com.solvd.musichall.models.event.Concert;
import com.solvd.musichall.models.services.CleanService;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
    private int scenarioID;
    private String name;
    private int capability;
    private List<Concert> concerts;
    private List<Seat> seats;
    private List<CleanService> cleanServices;

    /*
     * Constructors
     */
    public Scenario() {
        this.seats = new ArrayList<Seat>();
        this.concerts = new ArrayList<Concert>();
        this.cleanServices = new ArrayList<CleanService>();
    }

    public Scenario(String name, int capability) {
        this.name = name;
        this.capability = capability;
        this.seats = new ArrayList<Seat>();
        this.concerts = new ArrayList<Concert>();
        this.cleanServices = new ArrayList<CleanService>();
    }

    public Scenario(int scenarioID, String name, int capability) {
        this.scenarioID = scenarioID;
        this.name = name;
        this.capability = capability;
        this.seats = new ArrayList<Seat>();
        this.concerts = new ArrayList<Concert>();
        this.cleanServices = new ArrayList<CleanService>();
    }

    /*
     * Methods
     */

    public void addConcert(Concert concert) {
        concerts.add(concert);
    }

    public void addSeats(Seat seat) {
        seats.add(seat);
    }

    public void addCleanService(CleanService cleanService) {
        cleanServices.add(cleanService);
    }

    /*
        Getters and Setters
     */

    public int getScenarioID() {
        return scenarioID;
    }

    public String getName() {
        return name;
    }

    public int getCapability() {
        return capability;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public List<Concert> getConcerts() {
        return concerts;
    }

    public List<CleanService> getCleanServices() {
        return cleanServices;
    }

    public void setScenarioID(int scenarioID) {
        this.scenarioID = scenarioID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapability(int capability) {
        this.capability = capability;
    }

    /*
     * Override methods
     */

    @Override
    public String toString() {
        return "Scenario{" +
                "scenarioID=" + scenarioID +
                ", name='" + name + '\'' +
                ", capability=" + capability +
                ", concerts=" + concerts +
                ", seats=" + seats +
                '}';
    }
}
