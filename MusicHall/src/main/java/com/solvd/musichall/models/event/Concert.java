package com.solvd.musichall.models.event;

import com.solvd.musichall.models.people.Person;
import com.solvd.musichall.models.services.ConcertService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Concert {
    private int concertID;
    private float duration;
    private Band band;
    private Map<Person, Tickets> audience;
    private List<ConcertService> services;

    /*
     * Constructors
     */

    public Concert() {
        this.audience = new HashMap<>();
        this.services = new ArrayList<>();
    }

    public Concert(float duration) {
        this.duration = duration;
        this.audience = new HashMap<>();
        this.services = new ArrayList<>();

    }

    public Concert(float duration, Band band) {
        this.duration = duration;
        this.band = band;
        this.audience = new HashMap<>();
        this.services = new ArrayList<>();
    }

    public Concert(int concertID, float duration, Band band) {
        this.concertID = concertID;
        this.duration = duration;
        this.band = band;
        this.audience = new HashMap<>();
        this.services = new ArrayList<>();
    }

    /*
     * Getters and Setters
     */

    public int getConcertID() {
        return concertID;
    }

    public float getDuration() {
        return duration;
    }

    public Band getBand() {
        return band;
    }

    public Map<Person, Tickets> getAudience() {
        return audience;
    }

    public List<ConcertService> getServices() {
        return services;
    }

    public void setConcertID(int concertID) {
        this.concertID = concertID;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    /*
     * Override methods
     */

    @Override
    public String toString() {
        return "Concert{" +
                "concertID=" + concertID +
                ", duration=" + duration +
                ", band=" + band +
                ", audience=" + audience +
                ", services=" + services +
                '}';
    }
}