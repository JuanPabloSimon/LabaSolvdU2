package com.solvd.musichall.models.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Drummer extends MusicianDecorator {
    private static final Logger LOGGER = LogManager.getLogger(Drummer.class);

    public Drummer(IPerform performer) {
        super(performer);
    }

    @Override
    public void perform() {
        super.perform();
        LOGGER.info("Drums on!");
    }
}
