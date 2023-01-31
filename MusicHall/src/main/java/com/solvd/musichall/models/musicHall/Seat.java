package com.solvd.musichall.models.musicHall;

public class Seat {
    private int seatID;
    private int numeration;
    private boolean reserved;

    /*
     * Constructor
     */

    public Seat() {
    }

    public Seat(int numeration, boolean reserved) {
        this.numeration = numeration;
        this.reserved = reserved;
    }

    public Seat(int seatID, int numeration, boolean reserved) {
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

    @Override
    public String toString() {
        return "Seat{" +
                "seatID=" + seatID +
                ", numeration=" + numeration +
                ", reserved=" + reserved +
                '}';
    }
}
