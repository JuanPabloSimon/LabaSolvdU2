package com.solvd.musichall;

import com.solvd.musichall.dao.mysql.PersonDAO;
import com.solvd.musichall.utils.ConnectionPool2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        LOGGER.info("Starting program.");
        Connection c = ConnectionPool2.getInstance().getConnection();

//            Person person = new Person("Freddie", "Mercury", 475, 24);
//            LOGGER.info("Creating person with id " + person.getId() + ".");
        PersonDAO personDAO = new PersonDAO(c);
//            personDAO.create(person);

        LOGGER.info("Person found:\n" + personDAO.getAll());

    }
}
