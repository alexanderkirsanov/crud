package ru.susu.crud.database.dataset;

import ru.susu.crud.database.commands.FieldInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dataset {

    private final Map<Field, FieldInfo> mapOfFieldsInfo = new HashMap<Field, FieldInfo>();
    private Map<String, List<String>> mapOfData = new HashMap<String, List<String>>();


    public Dataset(List<Field> fields) {
        for (Field field : fields) {
            mapOfData.put(field.getName(), new ArrayList<String>());
        }
    }

    public void insertLine(String[] line) throws Exception {
        int i = 0;
        if (line.length != mapOfData.keySet().size()){
            throw new Exception("Incorrect Line");
        }
        for (List<String> value : mapOfData.values()) {
            value.add(line[i]);
            i++;
        }
    }

    public void clear() {
        mapOfData.clear();
        mapOfFieldsInfo.clear();
    }

    public void removeLine(int linePosition) {
        for (List<String> value : mapOfData.values()) {
            value.remove(linePosition);
        }
    }


    public String[] getLine(int linePosition) {
        String[] line = new String[mapOfData.size()];
        int i=0;
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
}
