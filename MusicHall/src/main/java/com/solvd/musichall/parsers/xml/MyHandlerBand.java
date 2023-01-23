package com.solvd.musichall.parsers.xml;

import com.solvd.musichall.models.event.Band;
import com.solvd.musichall.models.people.Musician;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class MyHandlerBand extends DefaultHandler {

    // List to hold Employees object
    private List<Band> bandList = null;
    private List<Musician> musicianList = null;
    private Band band = null;
    private Musician musician = null;
    private StringBuilder data = null;

    // getter method for employee list
    public List<Band> getBandList() {
        return bandList;
    }

    public List<Musician> getMusicianList() {
        return musicianList;
    }


    boolean bName = false;
    boolean bGenre = false;
    boolean bMembersAmount = false;
    boolean bMembers = false;

    boolean mRole = false;
    boolean mName = false;
    boolean mLastname = false;
    boolean mAge = false;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName.toLowerCase()) {
            case "band":
                // create a new Band and put it in Map
                String id = attributes.getValue("id");
                // initialize object and set id attribute
                band = new Band();
                band.setBandID(Integer.parseInt(id));
                // initialize list
                if (bandList == null)
                    bandList = new ArrayList<>();
                break;
            case "bandname":
                bName = true;
                break;
            case "genre":
                bGenre = true;
                break;
            case "membersamount":
                bMembersAmount = true;
                break;
            case "members":
                break;
            case "musician":
                String idMusician = attributes.getValue("id");
                musician = new Musician();
                musician.setId(Integer.parseInt(idMusician));
                break;
            case "role":
                mRole = true;
                break;
            case "name":
                mName = true;
                break;
            case "lastname":
                mLastname = true;
                break;
            case "age":
                mAge = true;
                break;
        }
        // create the data container
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (bName) {
            band.setName(data.toString());
            bName = false;
        } else if (bGenre) {
            band.setGenre(data.toString());
            bGenre = false;
        } else if (bMembersAmount) {
            band.setMembersAmount(Integer.parseInt(data.toString()));
            bMembersAmount = false;
        } else if (bMembers) {
            bMembers = false;
        } else if (mRole) {
            musician.setRole(data.toString());
            mRole = false;
        } else if (mName) {
            musician.setName(data.toString());
            mName = false;
        } else if (mLastname) {
            musician.setLastname(data.toString());
            mLastname = false;
        } else if (mAge) {
            musician.setAge(Integer.parseInt(data.toString()));
            mAge = false;
        }

        if (qName.equalsIgnoreCase("Band")) {
            // add Band object to list
            bandList.add(band);
        } else if (qName.equalsIgnoreCase("musician")) {
            band.addMember(musician);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}
