package com.solvd.musichall.mybatis;

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

public class BandService {
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

    public Band getByID(int id) {
        Band band = null;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IBandDAO scientistDAO = session.getMapper(IBandDAO.class);
            band = scientistDAO.getByID(id);//this doesn't get the scientist with its Assistants collections

            LOGGER.info("Get all Scientist finish successfully");
        }
        return band;
    }
}
