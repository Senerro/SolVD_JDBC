package com.solvd.repairService.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;


public final class ConnectionPool {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private final String url;
    private final String username;
    private final String password;
    private final ConcurrentLinkedDeque<Connection> freeConnections = new ConcurrentLinkedDeque<>();
    private final ConcurrentLinkedDeque<Connection> occupiedConnections = new ConcurrentLinkedDeque<>();

    private static ConnectionPool connectionPool;
    public int maxSize;

    public static synchronized ConnectionPool getConnectionPool(int size, ConnectionObject setting) {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool(size, setting);
        }
        return connectionPool;
    }

    private ConnectionPool(int size, ConnectionObject setting) {
        this.maxSize = size;
        if (size <= 1)
            this.maxSize = 5;
        this.url = setting.url();
        this.username = setting.username();
        this.password = setting.password();
    }

    public synchronized Connection getConnection() {
        Connection connection;

        connection = getPoolConnection();

        if (connection == null && occupiedConnections.size() < this.maxSize)
            connection = createPoolConnection();

        if (connection == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                LOGGER.error("thread " + Thread.currentThread() + " wasn't able to wait free connection");
                throw new RuntimeException(e);
            }
            connection = getPoolConnection();
        }
        return connection;
    }

    private Connection createPoolConnection() {
        var connection = createConnection();
        occupiedConnections.add(connection);
        return connection;
    }

    private Connection createConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            LOGGER.error("fail connection to database:\n"
                    + "url " + url + "\n"
                    + "username " + username + "\n"
                    + "password " + password);
            throw new RuntimeException(e);
        }
        return connection;
    }

    private Connection getPoolConnection() {
        Connection connection = null;
        if (!freeConnections.isEmpty()) {
            connection = freeConnections.pop();
            occupiedConnections.add(connection);
        }
        return connection;
    }

    public synchronized void returnConnection(Connection connection) {
        occupiedConnections.remove(connection);
        freeConnections.push(connection);
        notify();
    }
}

