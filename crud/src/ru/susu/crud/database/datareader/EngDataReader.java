package ru.susu.crud.database.datareader;

import ru.susu.crud.database.ConnectionProperties;

public class EngDataReader {
    private String sql;
    private ConnectionProperties connectionProperties;

    public EngDataReader(String sql, ConnectionProperties connectionProperties){
        this.sql = sql;
        this.connectionProperties = connectionProperties;

    }
}
