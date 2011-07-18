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
        for (List<String> value : mapOfData.values()) {
            line[i] = value.get(linePosition);
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
        Map<String, String> mapOfParameters = new HashMap<String, String>();
        if (line.keySet().size() != listOfField.size()) throw new Exception("Incorrect Line");
        int i = 0;
        for (Field field : listOfField) {
            FieldInfo fieldInfo = engCommandImp.getFieldInfo(field);
            mapOfParameters.put(engCommandImp.getFieldFullName(fieldInfo), engCommandImp.getFieldValueForInsert(fieldInfo, line.get(field), false));
            i++;
        }
        String insertCommand = new InsertCommand(listOfField.get(0).getSourceTable()).createCommand(mapOfParameters);
        EngDataWriter engDataWriter = new EngDataWriter(connectionManager, insertCommand);
        engDataWriter.executeInsert();
        selectData();
    }

    public void updateData(int lineNumber, Map<Field, String> line) throws Exception {
        Map<String, String> mapOfParameters = new HashMap<String, String>();
        Map<String, String> mapOfOldParameters = new HashMap<String, String>();
        if (line.keySet().size() != listOfField.size()) throw new Exception("Incorrect Line");
        for (Field field : listOfField) {
            FieldInfo fieldInfo = engCommandImp.getFieldInfo(field);
            mapOfParameters.put(engCommandImp.getFieldFullName(fieldInfo), engCommandImp.getFieldValueForInsert(fieldInfo, line.get(field), false));
            String oldParameter = engCommandImp.getFieldFullName(fieldInfo);
            String value = mapOfData.get(fieldInfo.getName()).get(lineNumber);
            String oldParameterValue = engCommandImp.getFieldValueForInsert(fieldInfo, value, false);
            mapOfOldParameters.put(oldParameter, oldParameterValue);
        }
        String updateCommand = new UpdateCommand(listOfField.get(0).getSourceTable()).createCommand(mapOfOldParameters, mapOfParameters);
        EngDataWriter engDataWriter = new EngDataWriter(connectionManager, updateCommand);
        engDataWriter.executeUpdate();
        selectData();
    }

    public void deleteData(int lineNumber) throws Exception {
        Map<String, String> mapOfParameters = new HashMap<String, String>();

        for (Field field : listOfField) {
            FieldInfo fieldInfo = engCommandImp.getFieldInfo(field);
            mapOfParameters.put(engCommandImp.getFieldFullName(fieldInfo),
                    engCommandImp.getFieldValueForDelete(fieldInfo,
                            mapOfData.get(field.getName()).get(lineNumber)));
        }
        String deleteCommand = new DeleteCommand(listOfField.get(0).getSourceTable()).createCommand(mapOfParameters);
        EngDataWriter engDataWriter = new EngDataWriter(connectionManager, deleteCommand);
        engDataWriter.executeInsert();
        selectData();
    }

    public void selectData() throws Exception {
        String selectCommand = new SelectCommand(listOfField.get(0).getSourceTable()).createCommand(listOfField, filters, sortbyField, sortType);
        EngDataReader engDataReader = new EngDataReader(selectCommand, this, connectionManager);
        engDataReader.execute();
        dataUpdated();
    }


    public void dataUpdated() {

    }
}
