package com.solvd.musichall.models.event;

import com.solvd.musichall.models.musicHall.Seat;
import com.solvd.musichall.models.people.Person;

import java.util.Objects;

public class Ticket {
    private int ticketID;
    private float value;
    private Person person;
    private Seat seat;

    /*
     * Constructor
     */

    public Ticket() {
    }

    public Ticket(float value) {
        this.value = value;
    }

    public Ticket(float value, Person person, Seat seat) {
        this.value = value;
        this.person = person;
        this.seat = seat;
    }

    public Ticket(int ticketID, float value, Person person, Seat seat) {
        this.ticketID = ticketID;
        this.value = value;
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


    public Person getPerson() {
        return person;
    }

    public Seat getSeat() {
        return seat;
    }

    public int getSeatNumber() {
        return this.seat.getNumeration();
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public void setValue(float value) {
        this.value = value;
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    /*
     * Override methods
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket tickets = (Ticket) o;
        return ticketID == tickets.ticketID && Float.compare(tickets.value, value) == 0 && person.equals(tickets.person) && seat.equals(tickets.seat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketID, value, person, seat);
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "ticketID=" + ticketID +
                ", value=" + value +
                ", person=" + person +
                ", seat=" + seat +
                '}';
    }
}
