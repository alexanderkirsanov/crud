package ru.susu.crud.database.connection;

import ru.susu.crud.database.ConnectionProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private String userName;
    private String password;
    private String dbms;
    private String serverName;
    private String dbName;

    public ConnectionManager(ConnectionProperties connectionProperties) {
        this.dbms = "mysql";
        this.userName = connectionProperties.getUserName();
        this.password = connectionProperties.getPassword();
        this.serverName = connectionProperties.getServerName();
        this.dbName = connectionProperties.getDatabase();
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);
        return DriverManager.
                getConnection("jdbc:" + this.dbms + "://" + this.serverName + "/" + this.dbName, connectionProps);
    }
}
