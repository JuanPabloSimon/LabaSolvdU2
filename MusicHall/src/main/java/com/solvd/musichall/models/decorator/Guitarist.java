package com.solvd.musichall.models.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Guitarist extends MusicianDecorator {
    private static final Logger LOGGER = LogManager.getLogger(Guitarist.class);

    public Guitarist(IPerform performer) {
        super(performer);
    }

    @Override
    public void perform() {
        super.perform();
        LOGGER.info("Play Guitar! Rock and Roll");
    }
}
