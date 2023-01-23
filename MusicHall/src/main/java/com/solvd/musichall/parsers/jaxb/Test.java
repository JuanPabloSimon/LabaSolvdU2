package com.solvd.musichall.parsers.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Test")
public class Test {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "details")
    private String details;

    public Test() {

    }

    public Test(String name, String details) {
        this.name = name;
        this.details = details;
    }
}
