package ru.susu.crud.server;

import ru.susu.crud.configurator.IPage;
import ru.susu.crud.database.DatasetRepository;
import ru.susu.crud.database.connection.ConnectionManager;
import ru.susu.crud.database.dataset.Dataset;
import ru.susu.crud.database.dataset.Field;
import ru.susu.crud.editor.Editor;

import java.util.*;

public class CrudServiceManager implements IPage {
    private String tableName;
    private Dataset dataset;
    private List<Field> fields;
    private Map<String, String[]> mapOfData = new HashMap<String, String[]>();
    private ConnectionManager connectionManager;
    private Map<String, Map<String, Editor>> editors = new HashMap<String, Map<String, Editor>>();

    public String[] getTables() {
        String[] result = new String[DatasetRepository.getInstance().getTables().size()];
        int i = 0;
        for (String table : DatasetRepository.getInstance().getTables()) {
            result[i] = table;
            i++;
        }
        return result;
    }

    private void prepareDataset() {
        this.dataset = DatasetRepository.getInstance().getDataset(this.tableName);
        this.fields = DatasetRepository.getInstance().getFields(this.tableName);
        try {
            this.dataset.selectData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[][] getData(String tableName) {
        this.tableName = tableName;
        prepareDataset();
        String[][] result = null;
        int rowCount = dataset.getRowCount();
        if (rowCount > 0) {
            result = new String[rowCount][dataset.getLine(0).length];
            for (int i = 0; i < this.dataset.getRowCount(); i++) {
                int j = 0;
                for (String str : this.dataset.getLine(i)) {
                    result[i][j] = str;
                    j++;
                }
            }
        }
        return result;
    }


    public String[] getHeaders(String tableName) {
        this.tableName = tableName;
        prepareDataset();
        String[] result = new String[fields.size()];
        int i = 0;
        for (Field field : fields) {
            result[i] = field.getName();
            i++;
        }
        return result;
    }


    public void insertData(String tableName, String[] lines) {
        this.tableName = tableName;
        try {
            prepareDataset();
            Map<Field, String> mapOfValues = new HashMap<Field, String>();
            int i = 0;
            for (Field field : this.fields) {
                mapOfValues.put(field, lines[i]);
                i++;
            }
            this.dataset.insertData(mapOfValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateData(String tableName, int lineNumber, String[] newLine) {
        this.tableName = tableName;
        try {
            prepareDataset();
            Map<Field, String> mapOfValues = new HashMap<Field, String>();
            int i = 0;
            for (Field field : this.fields) {
                mapOfValues.put(field, newLine[i]);
                i++;
            }
            this.dataset.updateData(lineNumber, mapOfValues);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteData(String tableName, int lineNumber) {
        this.tableName = tableName;
        try {
            prepareDataset();
            this.dataset.deleteData(lineNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, String[]>> getEditors(String tableName) {
        this.tableName = tableName;
        prepareDataset();
        Map<String, Editor> editorSetting = this.editors.get(tableName);
        List<Map<String, String[]>> listOfEditors = new LinkedList<Map<String, String[]>>();
        for (Field field : this.fields) {
            listOfEditors.add(editorSetting.get(field.getName()).getDefinition());
        }
        return listOfEditors;
    }

    @Override
    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void addFields(String table, List<Field> fields) {
        Dataset dataset = new Dataset(fields, connectionManager);
        try {
            DatasetRepository.getInstance().add(table, dataset, fields);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addEditors(String table, Map<String, Editor> editorsMap) {
        this.editors.put(table, editorsMap);
    }
}
