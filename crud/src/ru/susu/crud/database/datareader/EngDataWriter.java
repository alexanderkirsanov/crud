package ru.susu.crud.database.datareader;

import ru.susu.crud.database.connection.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;

public class EngDataWriter {
    private ConnectionManager connectionManager;
    private String sql;

    public EngDataWriter(ConnectionManager connectionManager, String sql) {
        this.connectionManager = connectionManager;
        this.sql = sql;
    }

    public void executeInsert() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        java.sql.Connection connection = connectionManager.getConnection();
        connection.setAutoCommit(false);
        Statement iudStatement = connection.createStatement();
        iudStatement.executeUpdate(sql);
        iudStatement.close();

        connection.commit();
        connection.setAutoCommit(true);
    }

    public void executeUpdate() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        java.sql.Connection connection = connectionManager.getConnection();
        connection.setAutoCommit(false);
        Statement iudStatement = connection.createStatement();
        iudStatement.executeUpdate(sql);
        iudStatement.close();
        connection.commit();
        connection.setAutoCommit(true);
    }
}
