package com.solvd.musichall.mybatis;

import com.solvd.musichall.models.event.Band;
import com.solvd.musichall.mybatis.services.BandService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
        BandService bandService = new BandService();
        Band band = bandService.getByID(1);
        LOGGER.info("Band Founded" + band);

//        PersonService personService = new PersonService();
//        Person person = new Person("Juan", "Simon", 21);
//        personService.create(person);
//        List<Person> p = personService.getAll();
//        LOGGER.info("Persons founded: \n" + p);

//        MusicianService musicianService = new MusicianService();
//        List<Musician> musicians = musicianService.getMusiciansByBandId(2);
//        LOGGER.info("Musicians Found: \n" + musicians);
    }
}
