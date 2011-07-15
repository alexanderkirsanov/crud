package ru.susu.crud.database.dataset;

import ru.susu.crud.database.commands.EngCommandImp;
import ru.susu.crud.database.commands.FieldInfo;
import ru.susu.crud.database.commands.InsertCommand;
import ru.susu.crud.database.commands.filter.Filterable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dataset {
    private EngCommandImp engCommandImp = new EngCommandImp();
    private Map<String, List<String>> mapOfData = new HashMap<String, List<String>>();
    private Map<Field, Filterable> filters = new HashMap<Field, Filterable>();
    private List<Field> listOfField;


    public Dataset(List<Field> fields) {
        int i = 0;
        listOfField = fields;
        for (Field field : fields) {
            mapOfData.put(field.getName(), new ArrayList<String>());
            i++;
        }
    }

    public void addFilter(Field field, Filterable filterable) {
        this.filters.put(field, filterable);
    }

    public void insertLine(String[] line) throws Exception {
        int i = 0;
        if (line.length != mapOfData.keySet().size()) {
            throw new Exception("Incorrect Line");
        }
        for (List<String> value : mapOfData.values()) {
            value.add(line[i]);
            i++;
        }
    }

    public void clear() {
        mapOfData.clear();
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

    public void insertData(String[] line) throws Exception {
        Map<String, String> mapOfParameters = new HashMap<String, String>();
        if (line.length != listOfField.size()) throw new Exception("Incorrect Line");
        int i = 0;
        for (Field field : listOfField) {
            FieldInfo fieldInfo = getFieldInfo(field);
            mapOfParameters.put(engCommandImp.getFieldFullName(fieldInfo), engCommandImp.getFieldValueForInsert(fieldInfo, line[i], false));
            i++;
        }
        String insertCommand = new InsertCommand(listOfField.get(0).getName()).createCommand(mapOfParameters);
        dataUpdated();
    }

    public void updateData(int lineNumber, String[] line) throws Exception {
        Map<String, String> mapOfParameters = new HashMap<String, String>();
        Map<String, String> mapOfOldParameters = new HashMap<String, String>();
        if (line.length != listOfField.size()) throw new Exception("Incorrect Line");
        if (lineNumber != mapOfData.keySet().size()) throw new Exception("Incorrect Line number");
        for (Field field : listOfField) {
            FieldInfo fieldInfo = getFieldInfo(field);
            mapOfParameters.put(engCommandImp.getFieldFullName(fieldInfo), engCommandImp.getFieldValueForInsert(fieldInfo, line[lineNumber], false));
            mapOfOldParameters.put(engCommandImp.getFieldFullName(fieldInfo), engCommandImp.getFieldValueForInsert(fieldInfo, mapOfData.get(fieldInfo.getName()).get(lineNumber), false));
        }


    }

    private FieldInfo getFieldInfo(Field field) {
        return new FieldInfo(field.getSourceTable(), field.getName(), field.getEngFieldType(), field.getAlias());

    }

    public void dataUpdated() {

    }
}
