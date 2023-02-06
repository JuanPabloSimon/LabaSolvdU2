package com.solvd.musichall.mybatis.services;

import com.solvd.musichall.models.event.Band;
import com.solvd.musichall.mybatis.interfaces.IBandDAO;
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
            IBandDAO bandDAO = session.getMapper(IBandDAO.class);
            band = bandDAO.getByID(id);
        }
        return band;
    }

    @Override
    public void create(Band band) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IBandDAO bandDAO = session.getMapper(IBandDAO.class);
            try {
                bandDAO.create(band);
                session.commit();
                LOGGER.info("Bands Created successfully");
            } catch (Exception e) {
                session.rollback();
                LOGGER.info(e.getMessage(), e);
            }
        }
    }

    @Override
    public void update(Band band) {

    }

    @Override
    public void deleteByID(int id) {

    }

    @Override
    public List<Band> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IBandDAO bandDAO = session.getMapper(IBandDAO.class);
            List<Band> bands = bandDAO.getAll();
            return bands;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
