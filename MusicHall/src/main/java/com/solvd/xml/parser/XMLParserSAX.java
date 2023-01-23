package com.solvd.xml.parser;

import com.solvd.musichall.models.event.Band;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class XMLParserSAX {

    public static void main(String[] args) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandlerBand handler = new MyHandlerBand();
            saxParser.parse(new File("musichall/src/main/resources/xml/band.xml"), handler);
            // Get Employees list
            List<Band> bandList = handler.getBandList();
            // print employee information
            for (Band band : bandList)
                System.out.println(band);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

}