package com.solvd.musichall.models.musicHall;

import com.solvd.musichall.models.people.Employee;

import java.util.ArrayList;
import java.util.Objects;

public class MusicHall {
    private String name;
    private int id;
    private int scenariosAmount;
    private ArrayList<Employee> employees;
    private ArrayList<Scenarios> scenarios;

    /*
     * Constructors
     */

    public MusicHall() {

    }

    public MusicHall(String name, int id, int scenariosAmount) {
        this.name = name;
        this.id = id;
        this.scenariosAmount = scenariosAmount;
        this.employees = new ArrayList<Employee>();
        this.scenarios = new ArrayList<Scenarios>();
    }

    /*
     * Getters and Setters
     */

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getScenariosAmount() {
        return scenariosAmount;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Scenarios> getScenarios() {
        return scenarios;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScenariosAmount(int scenariosAmount) {
        this.scenariosAmount = scenariosAmount;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void setScenarios(ArrayList<Scenarios> scenarios) {
        this.scenarios = scenarios;
    }

    /*
     * Override methods
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicHall musicHall = (MusicHall) o;
        return id == musicHall.id && scenariosAmount == musicHall.scenariosAmount && name.equals(musicHall.name) && Objects.equals(employees, musicHall.employees) && scenarios.equals(musicHall.scenarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, scenariosAmount, employees, scenarios);
    }

    @Override
    public String toString() {
        return "MusicHall{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", scenariosAmount=" + scenariosAmount +
                ", employees=" + employees.size() +
                '}';
    }
}
