package com.solvd.musichall.models.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.musichall.models.people.Musician;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "band")
@XmlAccessorType(XmlAccessType.FIELD)
public class Band {
    @JsonProperty
    @XmlAttribute(name = "id")
    private int bandID;
    @JsonProperty
    @XmlElement(name = "bandName")
    private String name;
    @JsonProperty
    @XmlElement(name = "genre")
    private String genre;
    @JsonProperty
    @XmlElement(name = "membersAmount")
    private int membersAmount;
    @JsonProperty
    @XmlElementWrapper(name = "members")
    @XmlElement(name = "musician")
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
     * Methods
     */

    public void addBandMember(Musician m) {
        this.members.add(m);
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

    public void setID(int bandID) {
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

    /*
     * Override methods
     */

    @Override
    public String toString() {
        return "Band{" +
                "bandID=" + bandID +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", membersAmount=" + membersAmount +
                ", members=" + members +
                '}';
    }
}
