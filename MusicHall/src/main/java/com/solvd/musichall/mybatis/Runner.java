package com.solvd.musichall.mybatis;

import com.solvd.musichall.models.event.Band;
import com.solvd.musichall.mybatis.services.BandService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
        BandService bandService = new BandService();
//        Band band = bandService.getByID(1);
        List<Band> bands = bandService.getAll();
        LOGGER.info("Band Founded " + bands);

//        PersonService personService = new PersonService();
//        Person person = new Person("Feli", "Simon", 18);
//        personService.create(person);
//        Person person2 = personService.getByID(19);
//        person2.setName("Juan Pablo");
//        personService.update(person2);
//        personService.deleteByID(20);
//        List<Person> p = personService.getAll();
//        LOGGER.info("Persons founded: \n" + p);

//        MusicianService musicianService = new MusicianService();
//        List<Musician> musicians = musicianService.getMusiciansByBandId(2);
//        LOGGER.info("Musicians Found: \n" + musicians);
    }
}
