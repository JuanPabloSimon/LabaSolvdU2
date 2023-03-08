package com.solvd.musichall.dao.mysql;

import com.solvd.musichall.utils.ConnectionPool;
import org.apache.ibatis.jdbc.SQL;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLDAO {
    protected Connection connection;

    public static Connection getConnection() throws SQLException {
         return ConnectionPool.getInstance().getConnection();
    }
}
