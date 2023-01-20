package com.solvd.musichall.models.people;

public class Employee extends Person {
    private int employeeID;
    private String role;

    /*
     * Constructors
     */

    public Employee() {
        super();
    }

    public Employee(String name, String lastname, int id, String role) {
        super(name, lastname, id);
        this.role = role;
    }

    public Employee(int employeeID, String name, String lastname, int id, String role) {
        super(name, lastname, id);
        this.employeeID = employeeID;
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

    /*
     * Override methods
     */

    @Override
    public String toString() {
        return "Employee{" +
                "role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
