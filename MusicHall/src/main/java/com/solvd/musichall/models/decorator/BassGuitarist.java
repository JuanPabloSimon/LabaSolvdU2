package com.solvd.musichall.models.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BassGuitarist extends MusicianDecorator {
    private static final Logger LOGGER = LogManager.getLogger(BassGuitarist.class);

    public BassGuitarist(IPerform performer) {
        super(performer);
    }

    @Override
    public void perform() {
        super.perform();
        LOGGER.info("Play Bass Guitar!!");
    }
}
