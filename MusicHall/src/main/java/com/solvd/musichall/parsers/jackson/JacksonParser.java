package com.solvd.musichall.parsers.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.musichall.models.event.Band;
import com.solvd.musichall.models.people.Musician;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JacksonParser {
    private static final Logger LOGGER = LogManager.getLogger(JacksonParser.class);
    private static final String FILE_PATH = "musichall/src/main/resources/outputFiles/band.json";

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        Band band = new Band(34, "Metallica", "Metal", 3);
        Musician musician = new Musician(63, "Pepe", "pepe", 21, 798, "Singer");
        Musician musician2 = new Musician(52, "Pepe2", "pepe2", 40, 799, "Guitar");
        Musician musician3 = new Musician(51, "Pepe3", "pepe3", 41, 800, "Bass Guitar");
        band.addBandMember(musician);
        band.addBandMember(musician2);
        band.addBandMember(musician3);
//        marshalling(mapper, band, FILE_PATH);

        LOGGER.info(unmarshalling(mapper, Band.class, FILE_PATH));
    }

    static void marshalling(ObjectMapper objMap, Object entity, String filepath) {
        try {
            objMap.writeValue(new File(filepath), entity);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    static Object unmarshalling(ObjectMapper objMap, Class c, String filepath) {
        Object entity;
        try {
            entity = objMap.readValue(new File(filepath), c);
            return entity;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
