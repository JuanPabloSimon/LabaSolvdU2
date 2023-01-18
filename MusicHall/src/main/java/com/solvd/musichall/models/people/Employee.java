package com.solvd.musichall.models.people;

public class Employee extends Person {
    private String role;

    public Employee(String role) {
        this.role = role;
    }

    public Employee(String name, String lastname, int id, String role) {
        super(name, lastname, id);
        this.role = role;
    }

    /*
     * Getters and Setters
     */

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
