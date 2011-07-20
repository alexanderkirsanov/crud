package ru.susu.crud.configurator;

import ru.susu.crud.database.ConnectionProperties;
import ru.susu.crud.database.connection.ConnectionManager;
import ru.susu.crud.database.dataset.*;
import ru.susu.crud.editor.Editor;
import ru.susu.crud.xml.Column;
import ru.susu.crud.xml.TableDefinition;
import ru.susu.crud.xml.XMLReader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PageConfigurator {

    private IPage servlet;

    public PageConfigurator(IPage servlet) {
        this.servlet = servlet;

    }

    public void configure() throws Exception {
        ConnectionProperties connectionProperties = new ConnectionProperties("localhost", "test", "lqip32", "4f3v6", 3306);
        ConnectionManager connectionManager = new ConnectionManager(connectionProperties);
        XMLReader xmlReader = new XMLReader("table.xml");
        Map<String, TableDefinition> tableDefinitionMap = xmlReader.getTables();

        this.servlet.setConnectionManager(connectionManager);
        for (TableDefinition tableDefinition : tableDefinitionMap.values()) {
            setDatasetFields(tableDefinition.getName(), tableDefinition.getColumns());
            setFieldEditors(tableDefinition.getName(), tableDefinition.getColumns());
        }
    }

    private void setDatasetFields(String table, List<Column> columns) throws Exception {
        List<Field> fields = new LinkedList<Field>();
        for (Column column : columns) {
            fields.add(createField(column, table));
        }
        servlet.addFields(table, fields);

    }

    private void setFieldEditors(String table, List<Column> columns) {
        Map<String, Editor> editorsMap = new HashMap<String, Editor>();
        for (Column column : columns) {
            editorsMap.put(column.getName(), createEditor(column, table));
        }
        servlet.addEditors(table, editorsMap);
    }

    private Editor createEditor(Column column, String table) {
        //TODO:implement
        return null;
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
}
