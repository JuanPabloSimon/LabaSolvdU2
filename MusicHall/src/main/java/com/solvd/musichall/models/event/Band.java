package com.solvd.musichall.models.event;

import com.solvd.musichall.models.people.Musician;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "band")
public class Band {
    @XmlAttribute(name = "id")
    private int bandID;
    @XmlElement(name = "bandName")
    private String name;
    @XmlElement(name = "genre")
    private String genre;
    @XmlElement(name = "membersAmount")
    private int membersAmount;
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

    public int getBandBandID() {
        return bandID;
    }

    public String getBandName() {
        return name;
    }

    public String getBandGenre() {
        return genre;
    }

    public int getBandMembersAmount() {
        return membersAmount;
    }

    public List<Musician> getBandMembers() {
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
