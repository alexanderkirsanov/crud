package ru.susu.crud.configurator;

import ru.susu.crud.database.connection.ConnectionManager;
import ru.susu.crud.database.dataset.*;
import ru.susu.crud.editor.DateEditor;
import ru.susu.crud.editor.Editor;
import ru.susu.crud.editor.TextEditor;
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

        XMLReader xmlReader = new XMLReader("table.xml");
        ConnectionManager connectionManager = new ConnectionManager(xmlReader.getConnectionProperties());
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

    private void setFieldEditors(String table, List<Column> columns) throws Exception {
        Map<String, Editor> editorsMap = new HashMap<String, Editor>();
        for (Column column : columns) {
            editorsMap.put(column.getName(), createEditor(column));
        }
        servlet.addEditors(table, editorsMap);
    }

    private Editor createEditor(Column column) throws Exception {
        String editor = column.getEditor().toLowerCase();
        int size = column.getSize();
        if (editor.equals("text")) {
            if (size < 1)
                return new TextEditor();
            else
                return new TextEditor(size);
        } else if (editor.equals("date")) {
            return new DateEditor();
        } else {
            throw new Exception("Incorrect XML file: editor did not exists");
        }
    }

    private Field createField(Column column, String table) throws Exception {
        String fieldType = column.getType().toLowerCase();
        if (fieldType.equals("integer")) {
            return new IntegerField(column.getName(), "", table, false);
        } else if (fieldType.equals("string")) {
            return new StringField(column.getName(), "", table, false);
        } else if (fieldType.equals("date")) {
            return new DateField(column.getName(), "", table, false);
        } else if (fieldType.equals("date_time")) {
            return new DateTimeField(column.getName(), "", table, false);
        } else if (fieldType.equals("time")) {
            return new TimeField(column.getName(), "", table, false);
        } else if (fieldType.equals("boolean")) {
            return new BooleanField(column.getName(), "", table, false);
        } else if (fieldType.equals("blob")) {
            return new BlobField(column.getName(), "", table, false);
        } else {
            throw new Exception("Incorrect XML file: type did not exists");
        }
    }


}
