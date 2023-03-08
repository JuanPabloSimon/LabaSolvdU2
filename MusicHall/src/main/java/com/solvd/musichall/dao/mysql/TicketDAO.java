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

    @Override
    public Ticket getByID(int id) {
        LOGGER.info(String.format("Searching Ticket with id: %d", id));
        Ticket t = null;
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_BY_ID = "SELECT * FROM tickets AS t " +
                    "INNER JOIN person AS p ON t.Person_idPerson = p.idPerson " +
                    "INNER JOIN seats AS s ON t.Seats_idSeats = s.idSeats " +
                    "WHEREidTickets = ?";
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
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
        try (Connection connection = MySQLDAO.getConnection();) {
            String CREATE = "INSERT INTO tickets (value, Concert_idConcert, Person_idPerson, Seats_idSeats) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
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
        try (Connection connection = MySQLDAO.getConnection();) {
            String UPDATE = "UPDATE tickets SET value= ?, Person_idPerson = ?, Seats_idSeats = ? WHERE idTickets = ?";
            PreparedStatement statement = connection.prepareStatement(UPDATE);
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
        try (Connection connection = MySQLDAO.getConnection();) {
            String DELETE_BY_ID = "DELETE FROM tickets WHERE idTickets = ?";
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
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
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_ALL = "SELECT * FROM tickets AS t " +
                    "INNER JOIN person AS p ON t.Person_idPerson = p.idPerson " +
                    "INNER JOIN seats AS s ON t.Seats_idSeats = s.idSeats";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
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
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_BY_CONCERT_ID = "SELECT * FROM tickets AS t " +
                    "INNER JOIN person AS p ON t.Person_idPerson = p.idPerson " +
                    "INNER JOIN seats AS s ON t.Seats_idSeats = s.idSeats " +
                    "WHERE Concert_idConcert = ?";
            PreparedStatement statement = connection.prepareStatement(GET_BY_CONCERT_ID);
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
        try (Connection connection = MySQLDAO.getConnection();) {
            String GET_BY_PERSON_ID = "SELECT * FROM tickets AS t " +
                    "INNER JOIN person AS p ON t.Person_idPerson = p.idPerson " +
                    "INNER JOIN seats AS s ON t.Seats_idSeats = s.idSeats " +
                    "WHERE Person_idPerson = ?";
            PreparedStatement statement = connection.prepareStatement(GET_BY_PERSON_ID);
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
