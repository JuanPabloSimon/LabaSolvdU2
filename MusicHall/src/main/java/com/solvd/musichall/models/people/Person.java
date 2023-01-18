package com.solvd.musichall.models.people;

import java.util.Objects;

public class Person {
    protected String name;
    protected String lastname;
    protected int age;
    protected int id;

    /*
     * Constructors
     */

    public Person() {

    }

    public Person(int id) {
        this.id = id;
    }

    public Person(String name, String lastname, int age) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public Person(String name, String lastname, int id, int age) {
        this.name = name;
        this.lastname = lastname;
        this.id = id;
        this.age = age;
    }

    /*
     * Getters and Setters
     */

    public String getName() {
        return this.name;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*
     * Override methods
     */

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && id == person.id && name.equals(person.name) && lastname.equals(person.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, age, id);
    }
}
