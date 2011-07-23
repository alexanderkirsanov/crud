package ru.susu.crud.database.commands;

import ru.susu.crud.database.commands.filter.FilterConditionGenerator;
import ru.susu.crud.database.commands.filter.Filterable;
import ru.susu.crud.database.dataset.Field;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SelectCommand {
    private String tableName;
    private Map<Field, Filterable> filters = new HashMap<Field, Filterable>();
    private List<Field> listOfFields;
    private int upLimit = -1;
    private int limitCount = -1;
    private Map<Field, String> orderBy = new HashMap<Field, String>();

    public SelectCommand(String tableName, List<Field> listOfFields) {
        this.tableName = tableName;
        this.listOfFields = listOfFields;
    }

    public String createCommand() throws Exception {
        StringBuilder selectCommand = new StringBuilder("SELECT ");
        selectCommand.append(getFieldsClause());
        selectCommand.append(" FROM ").append(tableName);
        if (getFiltersClause().length() != 0) {
            selectCommand.append(" WHERE ");
            selectCommand.append(getFiltersClause());
        }
        selectCommand.append(getOrderByClause());
        selectCommand.append(" ").append(getLimitClause());
        return selectCommand.toString();
    }

    protected StringBuilder getFieldsClause() {
        StringBuilder fieldsBuilder = new StringBuilder();
        Iterator<Field> fieldIterator = listOfFields.iterator();
        while (fieldIterator.hasNext()) {
            Field field = fieldIterator.next();
            fieldsBuilder.append(field.getName());
            if (fieldIterator.hasNext()) {
                fieldsBuilder.append(", ");
            }
        }
        return fieldsBuilder;
    }

    protected boolean hasOrdering() {
        return (orderBy.keySet().size() != 0);
    }

    protected StringBuilder getOrderByClause() {
        StringBuilder result = new StringBuilder();
        if (hasOrdering()) {
            boolean addOrderBy = false;
            StringBuilder ordersBuilder = new StringBuilder();
            Iterator<Field> ordersIterator = orderBy.keySet().iterator();
            while (ordersIterator.hasNext()) {
                Field field = ordersIterator.next();
                if (field != null) {
                    addOrderBy = true;
                    ordersBuilder.append(field.getName());
                    ordersBuilder.append(" ");
                    ordersBuilder.append(orderBy.get(field));
                    if (ordersIterator.hasNext()) {
                        ordersBuilder.append(", ");
                    }
                }
            }

            if (addOrderBy) {
                result.append(" ORDER BY ");
                result.append(ordersBuilder);
            }
        }
        return result;
    }

    protected StringBuilder getFiltersClause() throws Exception {
        StringBuilder filtersBuilder = new StringBuilder();
        FilterConditionGenerator filterConditionGenerator = new FilterConditionGenerator();
        Iterator<Field> whereIterator = filters.keySet().iterator();
        while (whereIterator.hasNext()) {
            Field currentField = whereIterator.next();
            filtersBuilder.append(filterConditionGenerator.createCondition(filters.get(currentField), currentField));
            if (whereIterator.hasNext()) {
                filtersBuilder.append(" AND ");
            }
        }
        return filtersBuilder;
    }

    public void addFieldFilter(Field field, Filterable filter) {
        filters.put(field, filter);
    }

    public int getUpLimit() {
        return upLimit;
    }

    public void setUpLimit(int upLimit) {
        this.upLimit = upLimit;
    }

    public int getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(int limitCount) {
        this.limitCount = limitCount;
    }

    public void addOrderBy(Field field, String type) {
        this.orderBy.put(field, type);
    }

    protected StringBuilder getLimitClause() {
        StringBuilder limitClauseBuilder = new StringBuilder();
        if ((limitCount >= 0) && (upLimit > 0)) {
            limitClauseBuilder.append(this.doGetLimitClause(limitCount, upLimit));
        }
        return limitClauseBuilder;
    }

    protected String doGetLimitClause(int limitCount, int upLimit) {
        return new EngCommandImp().getLimitClause(String.valueOf(limitCount), String.valueOf(upLimit));
    }


}
