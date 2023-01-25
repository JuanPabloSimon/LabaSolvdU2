package com.solvd.musichall.models.people;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "musician")
public class Musician extends Person {
    @JsonProperty("musicianId")
    @XmlAttribute(name = "id")
    private int musicianID;
    @JsonProperty("role")
    @XmlElement(name = "role")
    private String role;

    /*
     * Constructors
     */

    public Musician() {
        super();
    }

    public Musician(String name, String lastname, int personId, int age, String role) {
        super(name, lastname, personId);
        this.role = role;
    }


    public Musician(int musicianID, String name, String lastname, int age, int personId, String role) {
        super(personId, name, lastname, age);
        this.musicianID = musicianID;
        this.role = role;
    }

    /*
     * Getters and Setters
     */

    public String getRole() {
        return role;
    }

    public int getMusicianId() {
        return musicianID;
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
