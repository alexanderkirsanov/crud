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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConnectionManager that = (ConnectionManager) o;

        if (dbName != null ? !dbName.equals(that.dbName) : that.dbName != null) return false;
        if (dbms != null ? !dbms.equals(that.dbms) : that.dbms != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (serverName != null ? !serverName.equals(that.serverName) : that.serverName != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (dbms != null ? dbms.hashCode() : 0);
        result = 31 * result + (serverName != null ? serverName.hashCode() : 0);
        result = 31 * result + (dbName != null ? dbName.hashCode() : 0);
        return result;
    }
}
