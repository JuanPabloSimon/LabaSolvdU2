package com.solvd.musichall.service;

import com.solvd.musichall.dao.mysql.BandDAO;
import com.solvd.musichall.dao.mysql.MusicianDAO;
import com.solvd.musichall.models.event.Band;
import com.solvd.musichall.models.people.Musician;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;

public class BandService {
    private final static Logger LOGGER = LogManager.getLogger(BandDAO.class);
    private final Connection connection;
    private BandDAO bDAO;
    private MusicianDAO mDAO;

    public BandService(Connection connection) {
        this.connection = connection;
        this.bDAO = new BandDAO(connection);
        this.mDAO = new MusicianDAO(connection);
    }

    public Band getBandById(int id) {
        Band band = bDAO.getByID(id);
        ArrayList<Musician> musicians = mDAO.getMusiciansbyBandId(id);
        for (Musician m : musicians) {
            band.addBandMember(m);
        }
        return band;
    }
}