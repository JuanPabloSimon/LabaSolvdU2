package com.solvd.musichall.mybatis;

import com.solvd.musichall.models.people.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
//        BandService bandService = new BandService();
//        Band band = bandService.getByID(1);
//        LOGGER.info(band);

        PersonService personService = new PersonService();
        List<Person> p = personService.getAll();
        LOGGER.info("Persons founded: \n" + p);

    }
}
