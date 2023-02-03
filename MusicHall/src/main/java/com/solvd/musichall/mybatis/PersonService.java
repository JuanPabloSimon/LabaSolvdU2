package com.solvd.musichall.mybatis;

import com.solvd.musichall.dao.IPersonDAO;
import com.solvd.musichall.models.people.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class PersonService implements IPersonDAO {

    private final static Logger LOGGER = LogManager.getLogger(PersonService.class);
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Person getByID(int id) {
        return null;
    }

    @Override
    public Person create(Person person) {
        return null;
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public void deleteByID(int id) {

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
}
