package com.solvd.musichall.models.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.musichall.models.decorator.IPerform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "musician")
@XmlAccessorType(XmlAccessType.FIELD)
public class Musician extends Person implements IPerform {
    private static final Logger LOGGER = LogManager.getLogger(Musician.class);
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
     * Methods
     */

    @Override
    public void perform() {
        LOGGER.info("Get onto the stage");
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

    public void setMusicianID(int musicianID) {
        this.musicianID = musicianID;
    }

    /*
     * Override methods Object Class
     */


    @Override
    public String toString() {
        return "Musician{" +
                "name='" + name + " " + lastname + '\'' +
                "role='" + role + '\'' +
                '}';
    }

}
