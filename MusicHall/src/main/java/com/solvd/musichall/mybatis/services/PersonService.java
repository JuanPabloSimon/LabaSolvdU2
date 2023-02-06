package com.solvd.musichall.mybatis.services;

import com.solvd.musichall.models.people.Person;
import com.solvd.musichall.mybatis.MySessionFactory;
import com.solvd.musichall.mybatis.interfaces.IPersonDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PersonService implements com.solvd.musichall.mybatis.interfaces.IPersonDAO {

    private final static Logger LOGGER = LogManager.getLogger(PersonService.class);
    private final static SqlSessionFactory sqlSessionFactory = MySessionFactory.getInstance().getFactory();


    @Override
    public Person getByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IPersonDAO personDAO = session.getMapper(IPersonDAO.class);
            Person person = personDAO.getByID(id);
            return person;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }


    @Override
    public void update(Person person) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IPersonDAO personDAO = session.getMapper(IPersonDAO.class);
            try {
                personDAO.update(person);
                session.commit();
                LOGGER.info("Person Updated successfully");
            } catch (Exception e) {
                session.rollback();
                LOGGER.info(e.getMessage(), e);
            }
        }
    }

    @Override
    public void deleteByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            if (id > 0) {
                IPersonDAO personDAO = session.getMapper(IPersonDAO.class);
                personDAO.deleteByID(id);
                LOGGER.info("Person Deleted successfully");
                session.commit();
            }
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    @Override
    public List<Person> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IPersonDAO personDAO = session.getMapper(IPersonDAO.class);
            List<Person> persons = personDAO.getAll();
            return persons;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void create(Person person) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IPersonDAO personDAO = session.getMapper(IPersonDAO.class);
            try {
                personDAO.create(person);
                session.commit();
                LOGGER.info("Person Created successfully");
            } catch (Exception e) {
                session.rollback();
                LOGGER.info(e.getMessage(), e);
            }
        }
    }
}
