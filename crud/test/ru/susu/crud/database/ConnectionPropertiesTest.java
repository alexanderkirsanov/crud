package ru.susu.crud.database;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConnectionPropertiesTest {

    @Test
    public void testConnect() {
        String serverName = "localhost";
        String dbName = "test";
        String userName = "lqip32";
        String password = "4f3v6";
        int port = 3306;
        ConnectionProperties connectionProperties = new ConnectionProperties(
                serverName, dbName, userName, password, port);
        assertEquals(serverName, connectionProperties.getServerName());
        assertEquals(dbName, connectionProperties.getDatabase());
        assertEquals(userName, connectionProperties.getUserName());
        assertEquals(password, connectionProperties.getPassword());
        assertEquals(port, connectionProperties.getPort());
    }
}
