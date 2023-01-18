package com.solvd.musichall.models.people;

import java.util.Objects;

public class Musician extends Person {
    private String role;
    private int band_id;

    /*
     * Constructors
     */

    public Musician() {
        super();
    }

    public Musician(int id, int band_id, String role) {
        super(id);
        this.band_id = band_id;
        this.role = role;
    }

    public Musician(String name, String lastname, int id, int band_id, String role) {
        super(name, lastname, id);
        this.band_id = band_id;
        this.role = role;
    }

    /*
     * Getters and Setters
     */

    public String getRole() {
        return role;
    }

    public int getBand_id() {
        return band_id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setBand_id(int band_id) {
        this.band_id = band_id;
    }

    /*
     * Override methods
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Musician musician = (Musician) o;
        return band_id == musician.band_id && Objects.equals(role, musician.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), role, band_id);
    }

    @Override
    public String toString() {
        return "Musician{" +
                "name='" + name + " " + lastname + '\'' +
                "role='" + role + '\'' +
                ", band_id=" + band_id +
                '}';
    }
}
