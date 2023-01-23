package com.solvd.musichall.models.people;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "person")
public class Person {
    @XmlElement(name = "idPerson")
    protected int id;

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
        this.id = id;
    }

    public Person(String name, String lastname, int age) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public Person(int id, String name, String lastname, int age) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
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
