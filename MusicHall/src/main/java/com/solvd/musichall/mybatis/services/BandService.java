package com.solvd.musichall.mybatis.services;

import com.solvd.musichall.dao.IBandDAO;
import com.solvd.musichall.models.event.Band;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class BandService implements IBandDAO {
    private static final Logger LOGGER = LogManager.getLogger(BandService.class);
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
    public Band getByID(int id) {
        Band band = null;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IBandDAO scientistDAO = session.getMapper(IBandDAO.class);
            band = scientistDAO.getByID(id);
        }
        return band;
    }

    @Override
    public Band create(Band band) {
        return null;
    }

    @Override
    public Band update(Band band) {
        return null;
    }

    @Override
    public void deleteByID(int id) {

    }

    @Override
    public List<Band> getAll() {
        return null;
    }
}
