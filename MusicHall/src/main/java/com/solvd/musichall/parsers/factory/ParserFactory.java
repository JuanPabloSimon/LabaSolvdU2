package com.solvd.musichall.parsers.factory;

import com.solvd.musichall.parsers.jackson.JacksonParser;
import com.solvd.musichall.parsers.jaxb.JaxbFactory;
import com.solvd.musichall.parsers.xmlSAX.XMLParserSAX;

public class ParserFactory {

    public ParserFactory() {
    }

    public IParse getParser(ParserTypes types) {
        switch (types) {
            case JAXB:
                return new JaxbFactory();
            case JACKSON:
                return new JacksonParser();
            case SAX:
                return new XMLParserSAX();
            default:
                throw new RuntimeException("The type is not known for this program");
        }
    }
}
