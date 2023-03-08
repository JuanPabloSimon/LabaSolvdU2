package com.solvd.musichall.service;

import com.solvd.musichall.dao.mysql.BandDAO;
import com.solvd.musichall.dao.mysql.MusicianDAO;
import com.solvd.musichall.models.event.Band;
import com.solvd.musichall.models.people.Musician;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BandService {
    private final static Logger LOGGER = LogManager.getLogger(BandDAO.class);
    private BandDAO bDAO;
    private MusicianDAO mDAO;

    public BandService() {
        this.bDAO = new BandDAO();
        this.mDAO = new MusicianDAO();
    }

    public Band getBandById(int id) {
        Band band = bDAO.getByID(id);
        ArrayList<Musician> musicians = mDAO.getMusiciansByBandId(id);
        for (Musician m : musicians) {
            band.addBandMember(m);
        }
        return band;
    }

    public Band create(Band band) {
        bDAO.create(band);
        List<Musician> members = band.getMembers();
        for (Musician m : members) {
            mDAO.create(m);
        }
        return band;
    }
}
