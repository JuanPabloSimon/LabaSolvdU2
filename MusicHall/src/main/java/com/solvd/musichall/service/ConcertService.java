package com.solvd.musichall.service;

import com.solvd.musichall.dao.mysql.ConcertDAO;
import com.solvd.musichall.dao.mysql.ConcertServicesDAO;
import com.solvd.musichall.dao.mysql.TicketDAO;
import com.solvd.musichall.models.event.Concert;
import com.solvd.musichall.models.event.Ticket;
import com.solvd.musichall.models.musicHall.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ConcertService {
    private static final Logger LOGGER = LogManager.getLogger(ConcertService.class);
    private ConcertDAO concertDAO;
    private BandService bandService;
    private TicketDAO ticketDAO;
    private ConcertServicesDAO concertServicesDAO;

    public ConcertService() {
        this.concertDAO = new ConcertDAO();
        this.bandService = new BandService();
        this.ticketDAO = new TicketDAO();
        this.concertServicesDAO = new ConcertServicesDAO();
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

    public Concert create(Concert concert, Scenario s) {
        concertDAO.create(concert, s);
        List<Ticket> tickets = concert.getAudience();
        for (Ticket ticket : tickets) {
            ticketDAO.create(ticket);
        }
        List<com.solvd.musichall.models.services.ConcertService> services = concert.getServices();
        for (com.solvd.musichall.models.services.ConcertService service : services) {
            concertServicesDAO.create(service);
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
