package com.solvd.musichall.models.event;

import com.solvd.musichall.models.musicHall.Seats;
import com.solvd.musichall.models.people.Person;

import java.util.Objects;

public class Tickets {
    private int ticketID;
    private float value;
    private Concert concert;
    private Person person;
    private Seats seat;

    /*
     * Constructor
     */

    public Tickets() {
    }

    public Tickets(float value) {
        this.value = value;
    }

    public Tickets(float value, Concert concert, Person person, Seats seat) {
        this.value = value;
        this.concert = concert;
        this.person = person;
        this.seat = seat;
    }

    public Tickets(int ticketID, float value, Concert concert, Person person, Seats seat) {
        this.ticketID = ticketID;
        this.value = value;
        this.concert = concert;
        this.person = person;
        this.seat = seat;
    }

    /*
     * Getters and Setters
     */

    public int getTicketID() {
        return ticketID;
    }

    public float getValue() {
        return value;
    }

    public Concert getConcert() {
        return concert;
    }

    public Person getPerson() {
        return person;
    }

    public Seats getSeat() {
        return seat;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setSeat(Seats seat) {
        this.seat = seat;
    }

    /*
     * Override methods
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tickets tickets = (Tickets) o;
        return ticketID == tickets.ticketID && Float.compare(tickets.value, value) == 0 && concert.equals(tickets.concert) && person.equals(tickets.person) && seat.equals(tickets.seat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketID, value, concert, person, seat);
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "ticketID=" + ticketID +
                ", value=" + value +
                ", concert=" + concert +
                ", person=" + person +
                ", seat=" + seat +
                '}';
    }
}
