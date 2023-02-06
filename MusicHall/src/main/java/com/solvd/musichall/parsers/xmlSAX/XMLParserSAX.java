package com.solvd.musichall.parsers.xmlSAX;

import com.solvd.musichall.parsers.factory.IParse;
import com.solvd.musichall.parsers.factory.ParserTypes;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class XMLParserSAX implements IParse {
    private final String extension = ParserTypes.SAX.getExtension();
    private final SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    private final MyHandlerBand handler = new MyHandlerBand();


    @Override
    public void marshalling(Object entity, File file) {

    }

    @Override
    public Object unmarshalling(Class c, File file) {
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(file, handler);
            List<Object> bandList = handler.getBandList();
            for (Object band : bandList)
                return band;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getExtension() {
        return this.extension;
    }
}