package com.solvd.musichall.service;

import com.solvd.musichall.dao.mysql.ConcertDAO;
import com.solvd.musichall.dao.mysql.ConcertServicesDAO;
import com.solvd.musichall.dao.mysql.TicketDAO;
import com.solvd.musichall.models.event.Concert;
import com.solvd.musichall.models.event.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;

public class ConcertService {
    private static final Logger LOGGER = LogManager.getLogger(ConcertService.class);
    private final Connection connection;
    private ConcertDAO concertDAO;
    private BandService bandService;
    private TicketDAO ticketDAO;
    private ConcertServicesDAO concertServicesDAO;

    public ConcertService(Connection connection) {
        this.connection = connection;
        this.concertDAO = new ConcertDAO(connection);
        this.bandService = new BandService(connection);
        this.ticketDAO = new TicketDAO(connection);
        this.concertServicesDAO = new ConcertServicesDAO(connection);
    }

    public Concert getById(int id) {
        Concert concert = concertDAO.getByID(id);
        concert.setBand(bandService.getBandById(concert.getBand().getBandID()));
        ArrayList<Ticket> tickets = ticketDAO.getTicketsByConcertId(id);
        for (Ticket ticket : tickets) {
            concert.addTicket(ticket);
        }
        ArrayList<com.solvd.musichall.models.services.ConcertService> services = concertServicesDAO.getConcertServiceByConcertId(id);
        for (com.solvd.musichall.models.services.ConcertService service : services) {
            concert.addService(service);
        }
        return concert;
    }

    public ArrayList<Concert> getConcertByScenarioId(int id) {
        ArrayList<Concert> concerts = concertDAO.getConcertsByScenarioID(id);
        for (Concert c : concerts) {
            c.setBand(bandService.getBandById(c.getBand().getBandID()));
            ArrayList<Ticket> tickets = ticketDAO.getTicketsByConcertId(id);
            for (Ticket ticket : tickets) {
                c.addTicket(ticket);
            }
            ArrayList<com.solvd.musichall.models.services.ConcertService> services = concertServicesDAO.getConcertServiceByConcertId(c.getConcertID());
            for (com.solvd.musichall.models.services.ConcertService service : services) {
                c.addService(service);
            }
        }
        return concerts;
    }
}
