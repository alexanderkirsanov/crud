package ru.susu.crud.database.commands;

import ru.susu.crud.database.commands.filter.FilterConditionGenerator;
import ru.susu.crud.database.commands.filter.Filterable;
import ru.susu.crud.database.dataset.Field;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SelectCommand {
    private String tableName;

    public SelectCommand(String tableName) {
        this.tableName = tableName;
    }

    public String createCommand(List<Field> listOfField, Map<Field, Filterable> filters, Field sortByField, String sortType) throws Exception {
        StringBuilder selectCommand = new StringBuilder("SELECT ");
        Iterator<Field> fieldIterator = listOfField.iterator();
        while (fieldIterator.hasNext()) {
            Field field = fieldIterator.next();
            selectCommand.append(field.getName());
            if (fieldIterator.hasNext()) {
                selectCommand.append(", ");
            }
        }
        selectCommand.append(" FROM ").append(tableName);
        if (filters != null && filters.size() != 0) {
            FilterConditionGenerator filterConditionGenerator = new FilterConditionGenerator();
            selectCommand.append(" WHERE ");
            Iterator<Field> whereIterator = filters.keySet().iterator();
            while (whereIterator.hasNext()) {
                Field currentField = whereIterator.next();
                selectCommand.append(filterConditionGenerator.createCondition(filters.get(currentField), currentField));
                if (whereIterator.hasNext()) {
                    selectCommand.append(" AND ");
                }
            }
        }

        if (sortByField != null && (sortType.toUpperCase().equals("ASC") || sortType.toUpperCase().equals("DESC"))) {
            selectCommand.append(" ORDER BY ").append(sortByField.getName()).append(" ").append(sortType);
        }
        return selectCommand.toString();
    }
}
