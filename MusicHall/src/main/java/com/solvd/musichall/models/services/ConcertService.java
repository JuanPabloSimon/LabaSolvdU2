package com.solvd.musichall.models.services;

public class ConcertService {
    private int concertServiceID;
    private String name;
    private String type;

    /*
     * Constructor
     */
    public ConcertService() {
    }

    public ConcertService(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public ConcertService(int concertServiceID, String name, String type) {
        this.concertServiceID = concertServiceID;
        this.name = name;
        this.type = type;
    }

    /*
     * Getters and Setters
     */

    public int getConcertServiceID() {
        return concertServiceID;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setConcertServiceID(int concertServiceID) {
        this.concertServiceID = concertServiceID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    /*
     * Override methods
     */
    @Override
    public String toString() {
        return "ConcertService{" +
                "concertServiceID=" + concertServiceID +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
