package com.solvd.musichall.models.people;

import java.util.Objects;

public class Musician extends Person {
    private int musicianID;
    private String role;

    /*
     * Constructors
     */

    public Musician() {
        super();
    }

    public Musician(String name, String lastname, int id, String role) {
        super(name, lastname, id);
        this.role = role;
    }


    public Musician(int musicianID, String name, String lastname, int id, String role) {
        super(name, lastname, id);
        this.musicianID = musicianID;
        this.role = role;
    }

    /*
     * Getters and Setters
     */

    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    /*
     * Override methods
     */



    @Override
    public String toString() {
        return "Musician{" +
                "name='" + name + " " + lastname + '\'' +
                "role='" + role + '\'' +
                '}';
    }
}
