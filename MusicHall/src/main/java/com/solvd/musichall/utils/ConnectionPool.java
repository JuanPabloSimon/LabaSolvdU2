package com.solvd.musichall.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {
    private final String DB = "musichall";
    private final String URL = "jdbc:mysql://localhost:3306/" + DB;
    private final String USER = "root";
    private final String PASSWORD = "jsimon0301";

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool connectionPool;
    private final static BasicDataSource dataSource = new BasicDataSource();


    private ConnectionPool() throws SQLException {
        LOGGER.info("Reading properties file.");
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get("musichall/src/main/resources/mysqlDB.properties"))) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LOGGER.info("Creating connection pool to MySQL database");
        dataSource.setDriverClassName(properties.getProperty("driver"));
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setInitialSize(5);
    }

    public static ConnectionPool getInstance() {
        if (connectionPool == null) {
            try {
                connectionPool = new ConnectionPool();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            return connectionPool;
        }
        return connectionPool;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
