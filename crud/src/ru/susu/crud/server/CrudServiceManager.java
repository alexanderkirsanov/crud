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

    public List<String> getTables() {
//        prepareDataset();
        List<String> tables = new ArrayList<String>();
        for (String table : DatasetRepository.getInstance().getTables()) {
            tables.add(table);
        }
        return tables;
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

    public List<String[]> getData(String tableName) {
        this.tableName = tableName;
        List<String[]> result = new LinkedList<String[]>();
        prepareDataset();
        for (int i = 0; i < this.dataset.getRowCount(); i++) {
            result.add(i, this.dataset.getLine(i));
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


    public List<String> getFieldsForInsert(String currentTable) {
        this.tableName = currentTable;
        prepareDataset();
        List<String> result = new LinkedList<String>();
        for (Field field : this.fields) {
            result.add(field.getName());
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
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
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
