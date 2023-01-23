package com.solvd.musichall.parsers.jaxb;

import com.solvd.musichall.models.people.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class JaxbRunner {
    private static final Logger LOGGER = LogManager.getLogger(JaxbRunner.class);

    public static void main(String[] args) {
//        Band band = new Band(34, "Metallica", "Metal", 3);
//        Musician musician = new Musician(63, "Pepe", "pepe", 21, "Singer");
//        Musician musician2 = new Musician(52, "Pepe2", "pepe2", 40, "Guitar");
//        Musician musician3 = new Musician(51, "Pepe3", "pepe3", 41, "Bass Guitar");
//        band.addMember(musician);
//        band.addMember(musician2);
//        band.addMember(musician3);

        Person person = new Person(269, "Juan", "Simon", 21);
//        Test t = new Test("Juan", "About to quit");
        try {
            JAXBContext c = JAXBContext.newInstance(Person.class);
            Marshaller m = c.createMarshaller();
            m.marshal(m, new File("musichall/src/main/java/com/solvd/parsers/jaxb/person.xml"));
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }
}
