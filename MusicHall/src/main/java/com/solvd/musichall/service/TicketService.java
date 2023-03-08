package com.solvd.musichall.service;

import com.solvd.musichall.dao.mysql.TicketDAO;
import com.solvd.musichall.models.event.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TicketService {
    private static final Logger LOGGER = LogManager.getLogger(TicketService.class);
    private TicketDAO ticketDAO;

    public TicketService() {
        this.ticketDAO = new TicketDAO();
    }

    public Ticket getById(int id) {
        Ticket ticket = ticketDAO.getByID(id);
        return null;
    }
}
