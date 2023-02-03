package com.solvd.musichall.models.event;

import com.solvd.musichall.models.services.ConcertService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Concert {
    private int concertID;
    private float duration;
    private Band band;
    private Date date;
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

    public Concert(int concertID, float duration, Date date) {
        this.concertID = concertID;
        this.duration = duration;
        this.date = date;
        this.audience = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    public Concert(int concertID, float duration, Date date, Band band) {
        this.concertID = concertID;
        this.duration = duration;
        this.band = band;
        this.date = date;
        this.audience = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    /*
      Methods
     */

    public void addTicket(Ticket t) {
        this.audience.add(t);
    }

    public void addService(ConcertService c) {
        this.services.add(c);
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

    public Date getDate() {
        return date;
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

    public void setDate(Date date) {
        this.date = date;
    }

    /*
     * Override methods
     */

    @Override
    public String toString() {
        return "Concert{\n" +
                "  concertID=" + concertID +
                ",\n  duration=" + duration +
                ",\n  band=" + band +
                ",\n  date=" + date +
                ",\n  audience=" + audience +
                ",\n  services=" + services +
                "}\n";
    }
}