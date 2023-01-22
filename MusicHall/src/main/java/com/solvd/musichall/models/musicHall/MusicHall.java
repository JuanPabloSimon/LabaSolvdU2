package com.solvd.musichall.models.musicHall;

import com.solvd.musichall.models.people.Employee;

import java.util.ArrayList;
import java.util.Objects;

public class MusicHall {
    private int musicHallID;
    private String name;
    private int scenariosAmount;
    private ArrayList<Employee> employees;
    private ArrayList<Scenario> scenarios;

    /*
     * Constructors
     */

    public MusicHall() {

    }

    public MusicHall(String name, int scenariosAmount) {
        this.name = name;
        this.scenariosAmount = scenariosAmount;
        this.employees = new ArrayList<Employee>();
        this.scenarios = new ArrayList<Scenario>();
    }

    public MusicHall(int musicHallID, String name, int scenariosAmount) {
        this.name = name;
        this.musicHallID = musicHallID;
        this.scenariosAmount = scenariosAmount;
        this.employees = new ArrayList<Employee>();
        this.scenarios = new ArrayList<Scenario>();
    }

    /*
     * Methods
     */

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void addScenario(Scenario scenario) {
        this.scenarios.add(scenario);
    }

    /*
     * Getters and Setters
     */

    public String getName() {
        return name;
    }

    public int getId() {
        return musicHallID;
    }

    public int getScenariosAmount() {
        return scenariosAmount;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Scenario> getScenarios() {
        return scenarios;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.musicHallID = id;
    }

    public void setScenariosAmount(int scenariosAmount) {
        this.scenariosAmount = scenariosAmount;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void setScenarios(ArrayList<Scenario> scenarios) {
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
        return musicHallID == musicHall.musicHallID && scenariosAmount == musicHall.scenariosAmount && name.equals(musicHall.name) && Objects.equals(employees, musicHall.employees) && scenarios.equals(musicHall.scenarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, musicHallID, scenariosAmount, employees, scenarios);
    }

    @Override
    public String toString() {
        return "MusicHall{" +
                "name='" + name + '\'' +
                ", musicHalID=" + musicHallID +
                ", scenariosAmount=" + scenariosAmount +
                ", employees=" + employees.size() +
                '}';
    }
}
