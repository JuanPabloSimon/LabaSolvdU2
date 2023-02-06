package com.solvd.musichall.mybatis.services;

import com.solvd.musichall.dao.IMusicianDAO;
import com.solvd.musichall.models.people.Musician;
import com.solvd.musichall.mybatis.MySessionFactory;
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
        return null;
    }

    @Override
    public Musician create(Musician musician) {
        return null;
    }

    @Override
    public Musician update(Musician musician) {
        return null;
    }

    @Override
    public void deleteByID(int id) {

    }

    @Override
    public List<Musician> getAll() {
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
