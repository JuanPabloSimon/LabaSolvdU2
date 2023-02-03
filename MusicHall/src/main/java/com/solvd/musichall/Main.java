package com.solvd.musichall;

import com.solvd.musichall.service.BandService;
import com.solvd.musichall.service.ScenarioService;
import com.solvd.musichall.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        LOGGER.info("Starting program.");
        Connection c = ConnectionPool.getInstance().getConnection();


        // BAND TEST
        BandService bandService = new BandService(c);


        /* Create */
//        Band band = new Band("Metallica", "Metal", 3);
//        Musician musician = new Musician(63, "Pepe", "pepe", 21, 798, "Singer");
//        Musician musician2 = new Musician(52, "Pepe2", "pepe2", 40, 799, "Guitar");
//        Musician musician3 = new Musician(51, "Pepe3", "pepe3", 41, 800, "Bass Guitar");
//        band.addBandMember(musician);
//        band.addBandMember(musician2);
//        band.addBandMember(musician3);
//        LOGGER.info("Band Created: \n" + bandService.create(band).getBandID());
//        LOGGER.info("-------------------");
//        /* Select */
//        LOGGER.info("Band found:\n" + bandService.getBandById(2));
//        LOGGER.info("-------------------");


        // CONCERT TEST
//        ConcertService concertService = new ConcertService(c);

//        LOGGER.info("Concert found:\n" + concertService.getConcertByScenarioId(1));
//        LOGGER.info("-------------------");

        // SCENARIO TEST
        ScenarioService scenarioService = new ScenarioService(c);
        LOGGER.info("Scenario found:\n" + scenarioService.getScenarioById(1));


    }
}
