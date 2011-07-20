package ru.susu.crud;

import ru.susu.crud.database.ConnectionProperties;

public class TestConnectionProperties {
    public static ConnectionProperties getConnectionProperties() {
        return new ConnectionProperties("localhost", "test", "lqip32", "4f3v6", 3306);
    }
}
