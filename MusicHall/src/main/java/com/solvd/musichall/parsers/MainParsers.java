package com.solvd.musichall.parsers;

import com.solvd.musichall.models.event.Band;
import com.solvd.musichall.models.people.Musician;
import com.solvd.musichall.parsers.factory.IParse;
import com.solvd.musichall.parsers.factory.ParserFactory;
import com.solvd.musichall.parsers.factory.ParserTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class MainParsers {
    public static final Logger LOGGER = LogManager.getLogger(MainParsers.class);
    private static final String FILE_PATH = "musichall/src/main/resources/outputFiles/band";


    public static void main(String[] args) {
        ParserFactory factory = new ParserFactory();
        IParse parser = factory.getParser(ParserTypes.JAXB);


        Band band = new Band(34, "Metallica", "Metal", 3);
        Musician musician = new Musician(63, "Pepe", "pepe", 21, 798, "Singer");
        Musician musician2 = new Musician(52, "Pepe2", "pepe2", 40, 799, "Guitar");
        Musician musician3 = new Musician(51, "Pepe3", "pepe3", 41, 800, "Bass Guitar");
        band.addBandMember(musician);
        band.addBandMember(musician2);
        band.addBandMember(musician3);

        parser.marshalling(band, new File(FILE_PATH + parser.getExtension()));
        Object band2 = parser.unmarshalling(Band.class, new File(FILE_PATH + parser.getExtension()));
        LOGGER.info(band2);
    }
}
