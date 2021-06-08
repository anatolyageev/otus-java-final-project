package ru.otus.ageev.repository.db;

import java.sql.Connection;

public class JdbcConnectionHolder {
    private static final ThreadLocal<Connection> storeConnection = new ThreadLocal<>();

    public static Connection getConnection() {
        return storeConnection.get();
    }

    public static void setConnection(Connection connection) {
        storeConnection.set(connection);
    }

    public static void removeConnection() {
        storeConnection.remove();
    }
}
