package ru.susu.crud.database.commands;

import java.util.Iterator;
import java.util.Map;

public class InsertCommand {

    private String tableName;

    public InsertCommand(String tableName) {
        this.tableName = tableName;
    }

    public String createCommand(Map<String, String> parameters) {
        StringBuilder commandBuilder = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
        Iterator<String> parameterIterator = parameters.keySet().iterator();
        while (parameterIterator.hasNext()) {
            commandBuilder.append(parameterIterator.next());
            if (parameterIterator.hasNext()) {
                commandBuilder.append(", ");
            } else {
                commandBuilder.append(") ");
            }
        }
        commandBuilder.append("VALUES (");
        Iterator<String> valuesIterator = parameters.values().iterator();
        while (valuesIterator.hasNext()) {
            commandBuilder.append(valuesIterator.next());
            if (valuesIterator.hasNext()) {
                commandBuilder.append(", ");
            } else {
                commandBuilder.append(");");
            }
        }
        return commandBuilder.toString();
    }

}
