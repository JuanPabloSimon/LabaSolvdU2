package com.solvd.musichall.mybatis.services;

import com.solvd.musichall.models.people.Musician;
import com.solvd.musichall.mybatis.MySessionFactory;
import com.solvd.musichall.mybatis.interfaces.IMusicianDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MusicianService implements IMusicianDAO {
    private static final Logger LOGGER = LogManager.getLogger(MusicianService.class);
    private final SqlSessionFactory sqlSessionFactory = MySessionFactory.getInstance().getFactory();

    @Override
    public Musician getByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IMusicianDAO musicianDAO = session.getMapper(IMusicianDAO.class);
            Musician musician = musicianDAO.getByID(id);
            return musician;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void create(Musician musician) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IMusicianDAO musicianDAO = session.getMapper(IMusicianDAO.class);
            try {
                musicianDAO.create(musician);
                session.commit();
                LOGGER.info("Musician Created successfully");
            } catch (Exception e) {
                session.rollback();
                LOGGER.info(e.getMessage(), e);
            }
        }
    }

    @Override
    public void update(Musician musician) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IMusicianDAO musicianDAO = session.getMapper(IMusicianDAO.class);
            try {
                musicianDAO.update(musician);
                session.commit();
                LOGGER.info("Musician Updated successfully");
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
                IMusicianDAO musicianDAO = session.getMapper(IMusicianDAO.class);
                musicianDAO.deleteByID(id);
                LOGGER.info("Musician Deleted successfully");
                session.commit();
            }
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    @Override
    public List<Musician> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IMusicianDAO musicianDAO = session.getMapper(IMusicianDAO.class);
            List<Musician> musicians = musicianDAO.getAll();
            return musicians;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Musician> getMusiciansByBandId(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IMusicianDAO musicianDAO = session.getMapper(IMusicianDAO.class);
            List<Musician> musicians = musicianDAO.getMusiciansByBandId(id);
            return musicians;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
