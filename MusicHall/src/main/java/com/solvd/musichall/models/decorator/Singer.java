package com.solvd.musichall.models.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Singer extends MusicianDecorator {
    private static final Logger LOGGER = LogManager.getLogger(Singer.class);

    public Singer(IPerform performer) {
        super(performer);
    }

    @Override
    public void perform() {
        super.perform();
        LOGGER.info("Lets Sing!");
    }
}
