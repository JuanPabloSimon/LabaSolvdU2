package com.solvd.musichall.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;

public class MySessionFactory {
    private static final Logger LOGGER = LogManager.getLogger(MySessionFactory.class);
    private static MySessionFactory mySessionFactory;
    private SqlSessionFactory factory;

    private MySessionFactory() {
        String resource = "mybatis-config.xml";


        try {
            Reader reader = Resources.getResourceAsReader(resource);
            factory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static MySessionFactory getInstance() {
        if (mySessionFactory == null) {
            mySessionFactory = new MySessionFactory();
        }
        return mySessionFactory;
    }

    public SqlSessionFactory getFactory() {
        return factory;
    }
}
