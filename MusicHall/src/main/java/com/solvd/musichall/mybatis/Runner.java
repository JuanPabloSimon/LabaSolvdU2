package com.solvd.musichall.mybatis;

import com.solvd.musichall.models.event.Band;
import com.solvd.musichall.models.people.Musician;
import com.solvd.musichall.models.people.Person;
import com.solvd.musichall.mybatis.services.BandService;
import com.solvd.musichall.mybatis.services.MusicianService;
import com.solvd.musichall.mybatis.services.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
        BandService bandService = new BandService();

        Band band = bandService.getByID(1);
        LOGGER.info("Band Founded: " + band);
        LOGGER.info("-------------------");
        List<Band> bands = bandService.getAll();
        LOGGER.info("Bands Founded " + bands);

        PersonService personService = new PersonService();
        Person person = new Person("Feli", "Simon", 18);
        personService.create(person);
        Person person2 = personService.getByID(19);
        person2.setName("Juan Pablo");
        personService.update(person2);
        List<Person> p = personService.getAll();
        LOGGER.info("Persons founded: \n" + p);
        LOGGER.info("-------------------");

        /*
         You can try a deletion
         */
//        personService.deleteByID(); // dont forget to add an id
//        List<Person> p2 = personService.getAll();
//        LOGGER.info("Persons founded aftere deletion: \n" + p2);

        MusicianService musicianService = new MusicianService();
        List<Musician> musicians = musicianService.getMusiciansByBandId(2);
        LOGGER.info("Musicians Found: \n" + musicians);
    }
}
