package ru.susu.crud.database.commands;

import java.util.Iterator;
import java.util.Map;

public class UpdateCommand {
    private String tableName;

    public UpdateCommand(String tableName) {
        this.tableName = tableName;
    }


    public String createCommand(Map<String, String> mapOfOldParameters, Map<String, String> mapOfParameters) {
        StringBuilder commandBuilder = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
        Iterator<String> parameterIterator = mapOfParameters.keySet().iterator();
        while (parameterIterator.hasNext()) {
            String parameterName = parameterIterator.next();
            commandBuilder.append(parameterName).append(" = ").append(mapOfParameters.get(parameterName));
            if (parameterIterator.hasNext()) {
                commandBuilder.append(", ");
            }
        }
        commandBuilder.append(" WHERE ");
        Iterator<String> whereIterator = mapOfOldParameters.keySet().iterator();
        while (whereIterator.hasNext()) {
           String parameterName = whereIterator.next();
            commandBuilder.append(parameterName).append(" = ").append(mapOfOldParameters.get(parameterName));
            if (whereIterator.hasNext()) {
                commandBuilder.append(" AND ");
            }
        }
        return commandBuilder.toString();
    }
}
