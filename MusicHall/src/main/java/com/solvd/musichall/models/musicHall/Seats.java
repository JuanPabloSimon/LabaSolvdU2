package com.solvd.musichall.models.musicHall;

public class Seats {
    private int seatID;
    private int numeration;
    private boolean reserved;

    /*
     * Constructor
     */

    public Seats() {
    }

    public Seats(int numeration, boolean reserved) {
        this.numeration = numeration;
        this.reserved = reserved;
    }
    public Seats(int seatID, int numeration, boolean reserved) {
        this.seatID = seatID;
        this.numeration = numeration;
        this.reserved = reserved;
    }

    /*
     * Getters ans setters
     */

    public int getSeatID() {
        return seatID;
    }

    public int getNumeration() {
        return numeration;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public void setNumeration(int numeration) {
        this.numeration = numeration;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
