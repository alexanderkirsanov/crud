package ru.susu.crud.database.commands;

import ru.susu.crud.database.dataset.Field;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UpdateCommand {
    private String tableName;
    private Map<String, String> mapOfParameters = new HashMap<String, String>();
    private Map<String, String> mapOfOldParameters = new HashMap<String, String>();

    public UpdateCommand(String tableName) {
        this.tableName = tableName;
    }


    public String createCommand() {
        StringBuilder commandBuilder = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
        commandBuilder.append(getParameters());
        commandBuilder.append(" WHERE ");
        commandBuilder.append(getOldParameters());
        return commandBuilder.toString();
    }

    protected StringBuilder getParameters() {
        StringBuilder parametersBuilder = new StringBuilder();
        Iterator<String> parameterIterator = mapOfParameters.keySet().iterator();
        while (parameterIterator.hasNext()) {
            String parameterName = parameterIterator.next();
            parametersBuilder.append(parameterName).append(" = ").append(mapOfParameters.get(parameterName));
            if (parameterIterator.hasNext()) {
                parametersBuilder.append(", ");
            }
        }
        return parametersBuilder;
    }

    protected StringBuilder getOldParameters() {
        StringBuilder oldParametersBuilder = new StringBuilder();
        Iterator<String> whereIterator = mapOfOldParameters.keySet().iterator();
        while (whereIterator.hasNext()) {
            String parameterName = whereIterator.next();
            oldParametersBuilder.append(parameterName).append(" = ").append(mapOfOldParameters.get(parameterName));
            if (whereIterator.hasNext()) {
                oldParametersBuilder.append(" AND ");
            }
        }
        return oldParametersBuilder;
    }

    public void setParameters(Field field, String oldParameter, String newParameter) {
        EngCommandImp engCommandImp = new EngCommandImp();
        FieldInfo fieldInfo = engCommandImp.getFieldInfo(field);
        String parameterName = engCommandImp.getFieldFullName(fieldInfo);
        mapOfParameters.put(parameterName, newParameter);
        mapOfOldParameters.put(parameterName, oldParameter);

    }
}
