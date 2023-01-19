package com.solvd.musichall.models.event;

import com.solvd.musichall.models.people.Musician;

import java.util.ArrayList;
import java.util.List;

public class Band {
    private int bandID;
    private String name;
    private String genre;
    private int membersAmount;
    private List<Musician> members;

    /*
     * Constructors
     */
    public Band() {
        this.members = new ArrayList<Musician>();
    }

    public Band(String name, String genre, int membersAmount) {
        this.name = name;
        this.genre = genre;
        this.membersAmount = membersAmount;
        this.members = new ArrayList<Musician>();
    }

    public Band(int bandID, String name, String genre, int membersAmount) {
        this.bandID = bandID;
        this.name = name;
        this.genre = genre;
        this.membersAmount = membersAmount;
        this.members = new ArrayList<Musician>();
    }

    /*
     * Getters and Setters
     */

    public int getBandID() {
        return bandID;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getMembersAmount() {
        return membersAmount;
    }

    public List<Musician> getMembers() {
        return members;
    }

    public void setBandID(int bandID) {
        this.bandID = bandID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setMembersAmount(int membersAmount) {
        this.membersAmount = membersAmount;
    }
}
