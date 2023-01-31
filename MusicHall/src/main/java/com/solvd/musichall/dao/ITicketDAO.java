package com.solvd.musichall.dao;

import com.solvd.musichall.models.event.Ticket;

import java.util.List;

public interface ITicketDAO extends IBaseDAO<Ticket> {

    List<Ticket> getTicketsByConcertId(int id);

    Ticket getTicketByPersonId(int id);
}
