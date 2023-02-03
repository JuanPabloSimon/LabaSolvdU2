package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.dao.ITicketDAO;
import com.solvd.musichall.models.event.Concert;
import com.solvd.musichall.models.event.Ticket;
import com.solvd.musichall.models.musicHall.Seat;
import com.solvd.musichall.models.people.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO extends MySQLDAO implements ITicketDAO {
    private static final Logger LOGGER = LogManager.getLogger(TicketDAO.class);
    private final Connection connection;

    public TicketDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Ticket getByID(int id) {
        LOGGER.info(String.format("Searching Ticket with id: %d", id));
        Ticket t = null;
        try {
            String query = "select * from tickets as t " +
                    "inner join person as p on t.Person_idPerson = p.idPerson " +
                    "inner join seats as s on t.Seats_idSeats = s.idSeats " +
                    "where idTickets = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                t = new Ticket(
                        resultSet.getInt("idTickets"),
                        resultSet.getFloat("value"),
                        new Person(
                                resultSet.getInt("idPerson"),
                                resultSet.getString("name"),
                                resultSet.getString("lastname"),
                                resultSet.getInt("age")
                        ),
                        new Seat(
                                resultSet.getInt("idSeats"),
                                resultSet.getInt("number"),
                                resultSet.getBoolean("reserved")

                        )
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return t;
    }

    @Override
    public Ticket create(Ticket ticket) {
        return null;
    }

    public Ticket create(Ticket ticket, Concert concert) {
        LOGGER.info("Creating Ticket");
        try {
            String query = "insert into tickets (value, Concert_idConcert, Person_idPerson, Seats_idSeats) values (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setFloat(1, ticket.getValue());
            statement.setInt(2, concert.getConcertID());
            statement.setInt(2, ticket.getPerson().getId());
            statement.setInt(2, ticket.getSeat().getSeatID());
            statement.executeUpdate();

            ResultSet resultset = statement.getGeneratedKeys();
            while (resultset.next()) {
                ticket.setTicketID(resultset.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return ticket;
    }

    @Override
    public Ticket update(Ticket ticket) {
        LOGGER.info(String.format("Updating Ticket with id: %d", ticket.getTicketID()));
        try {
            String query = "update tickets set value= ?, Person_idPerson = ?, Seats_idSeats = ? where idTickets = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setFloat(1, ticket.getValue());
            statement.setInt(2, ticket.getPerson().getId());
            statement.setInt(3, ticket.getSeat().getSeatID());
            statement.setInt(3, ticket.getTicketID());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return ticket;
    }

    @Override
    public void deleteByID(int id) {
        LOGGER.info(String.format("Deleting ticket with id: %d", id));
        try {
            String query = "delete from tickets where idTickets = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Ticket> getAll() {
        LOGGER.info("Getting all tickets");
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        try {
            String query = "select * from tickets as t " +
                    "inner join person as p on t.Person_idPerson = p.idPerson " +
                    "inner join seats as s on t.Seats_idSeats = s.idSeats";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tickets.add(new Ticket(
                        resultSet.getInt("idTickets"),
                        resultSet.getFloat("value"),
                        new Person(
                                resultSet.getInt("idPerson"),
                                resultSet.getString("name"),
                                resultSet.getString("lastname"),
                                resultSet.getInt("age")
                        ),
                        new Seat(
                                resultSet.getInt("idSeats"),
                                resultSet.getInt("number"),
                                resultSet.getBoolean("reserved")

                        )
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return tickets;
    }

    @Override
    public ArrayList<Ticket> getTicketsByConcertId(int id) {
        LOGGER.info(String.format("Searching Ticket by concertId: %d", id));
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            String query = "select * from tickets as t " +
                    "inner join person as p on t.Person_idPerson = p.idPerson " +
                    "inner join seats as s on t.Seats_idSeats = s.idSeats " +
                    "where Concert_idConcert = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tickets.add(new Ticket(
                        resultSet.getInt("idTickets"),
                        resultSet.getFloat("value"),
                        new Person(
                                resultSet.getInt("idPerson"),
                                resultSet.getString("name"),
                                resultSet.getString("lastname"),
                                resultSet.getInt("age")
                        ),
                        new Seat(
                                resultSet.getInt("idSeats"),
                                resultSet.getInt("number"),
                                resultSet.getBoolean("reserved")

                        )
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return tickets;
    }

    @Override
    public Ticket getTicketByPersonId(int id) {
        LOGGER.info(String.format("Searching Musician by BandId: %d", id));
        Ticket t = null;
        try {
            String query = "select * from tickets as t " +
                    "inner join person as p on t.Person_idPerson = p.idPerson " +
                    "inner join seats as s on t.Seats_idSeats = s.idSeats " +
                    "where Person_idPerson = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                t = new Ticket(
                        resultSet.getInt("idTickets"),
                        resultSet.getFloat("value"),
                        new Person(
                                resultSet.getInt("idPerson"),
                                resultSet.getString("name"),
                                resultSet.getString("lastname"),
                                resultSet.getInt("age")
                        ),
                        new Seat(
                                resultSet.getInt("idSeats"),
                                resultSet.getInt("number"),
                                resultSet.getBoolean("reserved")

                        )
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return t;
    }
}
