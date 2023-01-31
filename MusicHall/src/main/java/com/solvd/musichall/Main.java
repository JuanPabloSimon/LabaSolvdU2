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
        LOGGER.info("Band found:\n" + bandService.getBandById(2));

        // SCENARIO TEST
        ScenarioService scenarioService = new ScenarioService(c);
        LOGGER.info("Scenario found:\n" + scenarioService.getScenarioById(1));
    }
}
