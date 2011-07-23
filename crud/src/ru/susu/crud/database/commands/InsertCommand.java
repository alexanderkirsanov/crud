package ru.susu.crud.database.commands;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InsertCommand {

    private String tableName;
    private Map<String, String> parameters = new HashMap<String, String>();

    public InsertCommand(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return this.tableName;
    }

    public String createCommand() {
        StringBuilder commandBuilder = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
        commandBuilder.append(getFields());
        commandBuilder.append("VALUES (");
        commandBuilder.append(getParameters());
        return commandBuilder.toString();
    }

    private StringBuilder getFields() {
        StringBuilder fieldBuilder = new StringBuilder();
        Iterator<String> parameterIterator = parameters.keySet().iterator();
        while (parameterIterator.hasNext()) {
            fieldBuilder.append(parameterIterator.next());
            if (parameterIterator.hasNext()) {
                fieldBuilder.append(", ");
            } else {
                fieldBuilder.append(") ");
            }
        }
        return fieldBuilder;
    }

    public void addParameters(String fieldName, String fieldValue) {
        parameters.put(fieldName, fieldValue);
    }


    public StringBuilder getParameters() {
        StringBuilder parametersBuilder = new StringBuilder();
        Iterator<String> valuesIterator = parameters.values().iterator();
        while (valuesIterator.hasNext()) {
            parametersBuilder.append(valuesIterator.next());
            if (valuesIterator.hasNext()) {
                parametersBuilder.append(", ");
            } else {
                parametersBuilder.append(");");
            }
        }
        return parametersBuilder;
    }
}
