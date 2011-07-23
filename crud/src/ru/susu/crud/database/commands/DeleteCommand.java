package ru.susu.crud.database.commands;

import ru.susu.crud.database.dataset.Field;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DeleteCommand {
    private String tableName;
    private Map<String, String> mapOfParameters = new HashMap<String, String>();

    public DeleteCommand(String tableName) {
        this.tableName = tableName;
    }


    public String createCommand() {
        StringBuilder commandBuilder = new StringBuilder("DELETE FROM ")
                .append(tableName)
                .append(" WHERE ")
                .append(getParameters());
        return commandBuilder.toString();
    }

    protected StringBuilder getParameters() {
        StringBuilder parameterBuilder = new StringBuilder();
        Iterator<String> parameterIterator = mapOfParameters.keySet().iterator();
        while (parameterIterator.hasNext()) {
            String parameterName = parameterIterator.next();
            parameterBuilder.append(parameterName).append(" = ").append(mapOfParameters.get(parameterName));
            if (parameterIterator.hasNext()) {
                parameterBuilder.append(" AND ");
            }
        }
        return parameterBuilder;
    }

    public void setParameters(Field field, String value) {
        EngCommandImp engCommandImp = new EngCommandImp();
        FieldInfo fieldInfo = engCommandImp.getFieldInfo(field);
        mapOfParameters.put(engCommandImp.getFieldFullName(fieldInfo), value);
    }
}
