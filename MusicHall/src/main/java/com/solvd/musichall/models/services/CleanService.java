package com.solvd.musichall.models.services;
public class CleanService {
    private int cleanServiceID;
    private String name;
    private String type;
    private float time;
    private float price;

    /*
     * Constructors
     */
    public CleanService() {

    }

    public CleanService(String name, String type, float time, float price) {
        this.name = name;
        this.type = type;
        this.time = time;
        this.price = price;
    }

    public CleanService(int cleanServiceID, String name, String type, float time, float price) {
        this.cleanServiceID = cleanServiceID;
        this.name = name;
        this.type = type;
        this.time = time;
        this.price = price;
    }

    /*
     * Getters and Setters
     */

    public int getCleanServiceID() {
        return cleanServiceID;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public float getTime() {
        return time;
    }

    public float getPrice() {
        return price;
    }

    public void setCleanServiceID(int cleanServiceID) {
        this.cleanServiceID = cleanServiceID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    /*
     * Override methods
     */

    @Override
    public String toString() {
        return "CleanService{" +
                "cleanServiceID=" + cleanServiceID +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", time=" + time +
                ", price=" + price +
                '}';
    }
}
