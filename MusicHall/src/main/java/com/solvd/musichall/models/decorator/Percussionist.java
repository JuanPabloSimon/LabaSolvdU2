package com.solvd.musichall.models.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Percussionist extends MusicianDecorator {
    private final static Logger LOGGER = LogManager.getLogger(Percussionist.class);

    public Percussionist(IPerform performer) {
        super(performer);
    }

    @Override
    public void perform() {
        super.perform();
        LOGGER.info("Add some percussion");
    }
}
