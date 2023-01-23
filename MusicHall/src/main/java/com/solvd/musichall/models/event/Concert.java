package com.solvd.musichall.models.event;

import com.solvd.musichall.models.services.ConcertService;

import java.util.ArrayList;
import java.util.List;

public class Concert {
    private int concertID;
    private float duration;
    private Band band;
    private List<Ticket> audience;
    private List<ConcertService> services;

    /*
     * Constructors
     */

    public Concert() {
        this.audience = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    public Concert(float duration) {
        this.duration = duration;
        this.audience = new ArrayList<>();
        this.services = new ArrayList<>();

    }

    public Concert(float duration, Band band) {
        this.duration = duration;
        this.band = band;
        this.audience = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    public Concert(int concertID, float duration, Band band) {
        this.concertID = concertID;
        this.duration = duration;
        this.band = band;
        this.audience = new ArrayList<>();
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

    public List<Ticket> getAudience() {
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