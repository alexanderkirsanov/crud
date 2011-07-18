package ru.susu.crud.database.commands;

import java.util.Iterator;
import java.util.Map;

public class DeleteCommand {
    private String tableName;

    public DeleteCommand(String tableName) {
        this.tableName = tableName;
    }


    public String createCommand(Map<String, String> mapOfParameters) {
        StringBuilder commandBuilder = new StringBuilder("DELETE FROM ").append(tableName).append(" WHERE ");
        Iterator<String> parameterIterator = mapOfParameters.keySet().iterator();
        while (parameterIterator.hasNext()) {
            String parameterName = parameterIterator.next();
            commandBuilder.append(parameterName).append(" = ").append(mapOfParameters.get(parameterName));
            if (parameterIterator.hasNext()) {
                commandBuilder.append(" AND ");
            }
        }
        return commandBuilder.toString();
    }
}
