package com.solvd.musichall.parsers.validator;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

public class MainXml {
    private static final Logger LOGGER = LogManager.getLogger(MainXml.class);
    private final static String XSD_PATH = "musichall/src/main/resources/xml/scenario.xsd";
    private final static String XML_PATH = "musichall/src/main/resources/xml/scenario.xml";


    public static void main(String[] args) throws SAXException {
        boolean valid = XSDValidator.validateXMLSchema(XSD_PATH, XML_PATH);
        LOGGER.info(String.format("XML file is %s", (valid ? "valid." : "invalid.")));
    }
}
