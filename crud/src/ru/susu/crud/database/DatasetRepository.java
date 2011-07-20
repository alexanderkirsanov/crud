package ru.susu.crud.database;

import ru.susu.crud.database.dataset.Dataset;
import ru.susu.crud.database.dataset.Field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DatasetRepository {

    private Map<String, Dataset> datasetMap = new HashMap<String, Dataset>();
    private Map<String, List<Field>> fieldListMap = new HashMap<String, List<Field>>();
    private static DatasetRepository datasetRepository;

    private DatasetRepository() {
    }

    public static DatasetRepository getInstance() {
        if (datasetRepository == null) {
            datasetRepository = new DatasetRepository();
        }
        return datasetRepository;
    }

    public void add(String tableName, Dataset dataset, List<Field> fieldsDefinition) throws Exception {
        fieldListMap.put(tableName, fieldsDefinition);
        datasetMap.put(tableName, dataset);
    }

    public Dataset getDataset(String tableName) {
        return this.datasetMap.get(tableName);
    }

    public List<Field> getFields(String tableName) {
        return this.fieldListMap.get(tableName);
    }

    public Set<String> getTables() {
        return this.fieldListMap.keySet();
    }
}

