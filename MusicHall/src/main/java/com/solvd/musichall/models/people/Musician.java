package com.solvd.musichall.models.people;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "musician")
public class Musician extends Person {
    @XmlAttribute(name = "id")
    private int musicianID;
    @XmlElement(name = "role")
    private String role;

    /*
     * Constructors
     */

    public Musician() {
        super();
    }

    public Musician(String name, String lastname, int personId, String role) {
        super(name, lastname, personId);
        this.role = role;
    }


    public Musician(int musicianID, String name, String lastname, int personId, String role) {
        super(name, lastname, personId);
        this.musicianID = musicianID;
        this.role = role;
    }

    /*
     * Getters and Setters
     */

    public String getBandRole() {
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
