package com.solvd.musichall.models.people;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "person")
public class Person {
    @XmlElement(name = "personId")
    protected int personId;

    @XmlElement(name = "name")
    protected String name;

    @XmlElement(name = "lastname")
    protected String lastname;

    @XmlElement(name = "age")
    protected int age;

    /*
     * Constructors
     */

    public Person() {
    }

    public Person(int id) {
        this.personId = id;
    }

    public Person(String name, String lastname, int age) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public Person(int id, String name, String lastname, int age) {
        this.personId = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    /*
     * Getters and Setters
     */

    public String getPersonName() {
        return this.name;
    }

    public String getPersonLastname() {
        return lastname;
    }

    public int getPersonAge() {
        return age;
    }

    public int getPersonId() {
        return personId;
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
        this.personId = id;
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
                ", id=" + personId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && personId == person.personId && name.equals(person.name) && lastname.equals(person.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, age, personId);
    }
}
