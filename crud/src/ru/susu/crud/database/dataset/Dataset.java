package ru.susu.crud.database.dataset;

import ru.susu.crud.database.commands.*;
import ru.susu.crud.database.commands.filter.Filterable;
import ru.susu.crud.database.connection.ConnectionManager;
import ru.susu.crud.database.datareader.EngDataReader;
import ru.susu.crud.database.datareader.EngDataWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dataset {
    private EngCommandImp engCommandImp = new EngCommandImp();
    private Map<String, List<String>> mapOfData = new HashMap<String, List<String>>();
    private Map<Field, Filterable> filters = new HashMap<Field, Filterable>();
    private List<Field> listOfField;
    private Field sortbyField;
    private String sortType;
    private ConnectionManager connectionManager;


    public Dataset(List<Field> fields, ConnectionManager connectionManager) {
        listOfField = fields;
        clear();
        this.connectionManager = connectionManager;

    }

    public void addSortBy(Field field, String type) {
        this.sortbyField = field;
        this.sortType = type;
    }

    public void clearSortType() {
        this.sortbyField = null;
        this.sortType = "";
    }

    public void addFilter(Field field, Filterable filterable) throws Exception {
        if (!listOfField.contains(field)) throw new Exception("IncorrectFilter");
        this.filters.put(field, filterable);
    }

    public void clearFilter() {
        this.filters.clear();
    }

    public void insertLine(String[] line) throws Exception {
        int i = 0;
        if (line.length != mapOfData.keySet().size()) {
            throw new Exception("Incorrect Line");
        }
        for (Field field : listOfField) {
            mapOfData.get(field.getName()).add(line[i]);
            i++;
        }
    }

    public void clear() {
        mapOfData.clear();
        for (Field field : listOfField) {
            mapOfData.put(field.getName(), new ArrayList<String>());
        }
    }

    public void removeLine(int linePosition) {
        for (List<String> value : mapOfData.values()) {
            value.remove(linePosition);
        }
    }


    public String[] getLine(int linePosition) {
        String[] line = new String[mapOfData.size()];
        int i = 0;
        for (Field field : listOfField) {
            line[i] = mapOfData.get(field.getName()).get(linePosition);
            i++;
        }
        return line;
    }

    public int getRowCount() {
        for (List<String> value : mapOfData.values()) {
            return value.size();
        }
        return 0;
    }

    public void insertData(Map<Field, String> line) throws Exception {
        if (line.keySet().size() != listOfField.size()) throw new Exception("Incorrect Line");
        InsertCommand insertCommand = new InsertCommand(listOfField.get(0).getSourceTable());
        for (Field field : listOfField) {
            FieldInfo fieldInfo = engCommandImp.getFieldInfo(field);
            insertCommand.addParameters(engCommandImp.getFieldFullName(fieldInfo), engCommandImp.getFieldValueForInsert(fieldInfo, line.get(field), false));
        }
        EngDataWriter engDataWriter = new EngDataWriter(connectionManager, insertCommand.createCommand());
        engDataWriter.executeInsert();
        selectData();
    }

    public void updateData(int lineNumber, Map<Field, String> line) throws Exception {
        Map<String, String> mapOfParameters = new HashMap<String, String>();
        Map<String, String> mapOfOldParameters = new HashMap<String, String>();
        if (line.keySet().size() != listOfField.size()) throw new Exception("Incorrect Line");
        UpdateCommand updateCommand = new UpdateCommand(listOfField.get(0).getSourceTable());
        for (Field field : listOfField) {
            FieldInfo fieldInfo = engCommandImp.getFieldInfo(field);
            String value = mapOfData.get(fieldInfo.getName()).get(lineNumber);
            String oldParameterValue = engCommandImp.getFieldValueForInsert(fieldInfo, value, false);
            String parameterValue = engCommandImp.getFieldValueForInsert(fieldInfo, line.get(field), false);
            updateCommand.setParameters(field, oldParameterValue, parameterValue);
        }
        EngDataWriter engDataWriter = new EngDataWriter(connectionManager, updateCommand.createCommand());
        engDataWriter.executeUpdate();
        selectData();
    }

    public void deleteData(int lineNumber) throws Exception {
        Map<String, String> mapOfParameters = new HashMap<String, String>();
        DeleteCommand deleteCommand = new DeleteCommand(listOfField.get(0).getSourceTable());
        for (Field field : listOfField) {
            FieldInfo fieldInfo = engCommandImp.getFieldInfo(field);
            deleteCommand.setParameters(field, engCommandImp.getFieldValueForDelete(fieldInfo,
                    mapOfData.get(field.getName()).get(lineNumber)));
        }

        EngDataWriter engDataWriter = new EngDataWriter(connectionManager, deleteCommand.createCommand());
        engDataWriter.executeInsert();
        selectData();
    }

    public void selectData() throws Exception {
        SelectCommand selectCommand = new SelectCommand(listOfField.get(0).getSourceTable(), listOfField);
        for (Field field : filters.keySet()) {
            selectCommand.addFieldFilter(field, filters.get(field));
        }
        selectCommand.addOrderBy(sortbyField, sortType);
        EngDataReader engDataReader = new EngDataReader(selectCommand.createCommand(), this, connectionManager);
        engDataReader.execute();
        dataUpdated();
    }


    public void dataUpdated() {

    }
}
