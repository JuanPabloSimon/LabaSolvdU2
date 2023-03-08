package com.solvd.musichall;

import com.solvd.musichall.dao.mysql.ConcertServicesDAO;
import com.solvd.musichall.models.decorator.*;
import com.solvd.musichall.models.event.Band;
import com.solvd.musichall.models.event.Concert;
import com.solvd.musichall.models.event.Ticket;
import com.solvd.musichall.models.musicHall.Scenario;
import com.solvd.musichall.models.musicHall.Seat;
import com.solvd.musichall.models.people.Musician;
import com.solvd.musichall.models.people.Person;
import com.solvd.musichall.models.services.ConcertService;
import com.solvd.musichall.service.BandService;
import com.solvd.musichall.service.ScenarioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        LOGGER.info("Starting program.");

        // BAND TEST
        BandService bandService = new BandService();

        /* Select */

        Band band = bandService.getBandById(2);
        LOGGER.info("Band found:\n" + band);
        LOGGER.info("-------------------");


        /*
         Musician (Decorator task)
         */

        IPerform musician = new Singer(new Guitarist(new Musician(63, "Pepe", "pepe", 21, 798, "Singer")));
        IPerform musician2 = new BassGuitarist(new Singer(new Musician(52, "Pepe2", "pepe2", 40, 799, "Guitar")));
        IPerform musician3 = new Drummer(new Percussionist(new Musician(51, "Pepe3", "pepe3", 41, 800, "Bass Guitar")));
        musician.perform();
        LOGGER.info("-------------");
        musician2.perform();
        LOGGER.info("-------------");
        musician3.perform();
        LOGGER.info("-------------");


        // SCENARIO TEST
        ScenarioService scenarioService = new ScenarioService();
        Scenario scenario = scenarioService.getScenarioById(1);
        LOGGER.info("Scenario with id 1" + scenario);
        LOGGER.info("-------------------");


        /*
         CONCERT TEST
         */

        Date date = new Date();
        Concert concert = new Concert(120, date, band);
        ConcertServicesDAO concertServicesDAO = new ConcertServicesDAO();
        List<ConcertService> services = concertServicesDAO.getAll();
        for (com.solvd.musichall.models.services.ConcertService s : services) {
            concert.addService(s);
        }

        Person p1 = new Person("John", "Fudge", 41);
        Person p2 = new Person("John", "Fudge", 41);
        Person p3 = new Person("John", "Fudge", 41);
        Seat s1 = new Seat(25, false);
        Seat s2 = new Seat(26, false);
        Seat s3 = new Seat(27, false);
        Ticket t1 = new Ticket(200, p1, s1);
        Ticket t2 = new Ticket(200, p2, s2);
        Ticket t3 = new Ticket(200, p3, s3);
        concert.addTicket(t1);
        concert.addTicket(t2);
        concert.addTicket(t3);

        com.solvd.musichall.service.ConcertService concertService = new com.solvd.musichall.service.ConcertService();

        LOGGER.info("Create Concert: " + concertService.create(concert, scenario));
        LOGGER.info("-------------------");


    }
}
