package com.solvd.musichall.service;

import com.solvd.musichall.dao.mysql.TicketDAO;
import com.solvd.musichall.models.event.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class TicketService {
    private static final Logger LOGGER = LogManager.getLogger(TicketService.class);
    private final Connection connection;
    private TicketDAO ticketDAO;

    public TicketService(Connection connection) {
        this.connection = connection;
        this.ticketDAO = new TicketDAO(connection);
    }

    public Ticket getById(int id) {
        Ticket ticket = ticketDAO.getByID(id);
        return null;
    }
}
