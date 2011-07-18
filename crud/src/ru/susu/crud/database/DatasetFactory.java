package ru.susu.crud.database;

import ru.susu.crud.database.connection.ConnectionManager;
import ru.susu.crud.database.dataset.*;
import ru.susu.crud.xml.Column;
import ru.susu.crud.xml.TableDefinition;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DatasetFactory {

    private Map<String, Dataset> datasetMap;
    private HashMap<String, List<Field>> fieldListMap;

    public DatasetFactory(Map<String, TableDefinition> tables, ConnectionManager connectionManager) throws Exception {
        datasetMap = new HashMap<String, Dataset>();
        fieldListMap = new HashMap<String, List<Field>>();
        for (TableDefinition tableDefinition : tables.values()) {
            String tableName = tableDefinition.getName();
            List<Field> fields = new LinkedList<Field>();
            for (Column column : tableDefinition.getColumns()) {
                fields.add(createField(column, tableName));
            }
            Dataset dataset = new Dataset(fields, connectionManager);
            fieldListMap.put(tableName, fields);
            datasetMap.put(tableName, dataset);
        }
    }

    private Field createField(Column column, String table) throws Exception {
        if (column.getType().toLowerCase().equals("integer")) {
            return new IntegerField(column.getName(), "", table, false);
        } else if (column.getType().toLowerCase().equals("string")) {
            return new StringField(column.getName(), "", table, false);
        } else if (column.getType().toLowerCase().equals("date")) {
            return new DateField(column.getName(), "", table, false);
        } else if (column.getType().toLowerCase().equals("date_time")) {
            return new DateTimeField(column.getName(), "", table, false);
        } else if (column.getType().toLowerCase().equals("time")) {
            return new TimeField(column.getName(), "", table, false);
        } else if (column.getType().toLowerCase().equals("boolean")) {
            return new BooleanField(column.getName(), "", table, false);
        } else if (column.getType().toLowerCase().equals("blob")) {
            return new BlobField(column.getName(), "", table, false);
        } else {
            throw new Exception("Incorrect XML file: type did not exists");
        }
    }

    public Dataset getDataset(String tableName) {
        return this.datasetMap.get(tableName);
    }

    public List<Field> getFields(String tableName) {
        return this.fieldListMap.get(tableName);
    }
}

