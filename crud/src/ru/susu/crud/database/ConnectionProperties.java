package ru.susu.crud.database;

public class ConnectionProperties {
    private String serverName;
    private String database;
    private String userName;
    private String password;
    private int port;

    public ConnectionProperties(String serverName, String database,
                                String userName, String password, int port) {
        super();
        this.serverName = serverName;
        this.database = database;
        this.userName = userName;
        this.password = password;
        this.port = port;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDatabase() {
        return database;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

}
