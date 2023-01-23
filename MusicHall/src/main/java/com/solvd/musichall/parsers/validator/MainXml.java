package com.solvd.musichall.parsers.validator;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

public class MainXml {
    private static final Logger LOGGER = LogManager.getLogger(MainXml.class);

    public static void main(String[] args) throws SAXException {
        String xsdPath = "musichall/src/main/resources/scenario.xsd";
        String xmlPath = "musichall/src/main/resources/scenario.xml";
        boolean valid = XSDValidator.validateXMLSchema(xsdPath, xmlPath);
        LOGGER.info(String.format("XML file is %s", (valid ? "valid." : "invalid.")));
    }
}
