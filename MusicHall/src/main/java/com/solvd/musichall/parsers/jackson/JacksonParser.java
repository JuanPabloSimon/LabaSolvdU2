package com.solvd.musichall.parsers.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.musichall.parsers.factory.IParse;
import com.solvd.musichall.parsers.factory.ParserTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JacksonParser implements IParse {
    private static final Logger LOGGER = LogManager.getLogger(JacksonParser.class);
    private final ObjectMapper mapper = new ObjectMapper();
    private final String extension = ParserTypes.JACKSON.getExtension();

    @Override
    public void marshalling(Object entity, File file) {
        try {
            mapper.writeValue(file, entity);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public Object unmarshalling(Class c, File file) {
        Object entity;
        try {
            entity = mapper.readValue(file, c);
            return entity;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public String getExtension() {
        return this.extension;
    }
}
