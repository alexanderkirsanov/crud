package ru.susu.crud.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.susu.crud.client.crudService;
import ru.susu.crud.components.EntityFinder;
import ru.susu.crud.configurator.Configurator;
import ru.susu.crud.configurator.IConfigurable;
import ru.susu.crud.database.DatasetRepository;
import ru.susu.crud.database.connection.ConnectionManager;
import ru.susu.crud.database.dataset.Dataset;
import ru.susu.crud.database.dataset.Field;

import java.util.*;

public class crudServiceImpl extends RemoteServiceServlet implements crudService, IConfigurable {
    EntityFinder finder = new EntityFinder();
    private ArrayList<String> sourceForView;
    private String tableName;
    private Dataset dataset;
    private List<Field> fields;
    private Map<String, String[]> mapOfData = new HashMap<String, String[]>();
    private ConnectionManager connectionManager;

    public crudServiceImpl() {
        try {
            new Configurator(this);
        } catch (Exception e) {
            System.out.println("die");
        }
    }

    @Override
    public List<String> getTables() {
        try {
            prepareDataset();
        } catch (Exception e) {

        }
        List<String> tables = new ArrayList<String>();
        for (String table : DatasetRepository.getInstance().getTables()) {
            tables.add(table);
        }
        return tables;
    }

    private void prepareDataset() throws Exception {

        this.dataset = DatasetRepository.getInstance().getDataset(this.tableName);
        this.fields = DatasetRepository.getInstance().getFields(this.tableName);
        this.dataset.selectData();
    }

    @Override
    public void setTable(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public Map<String, String> update() {
        Map<String, String> mapOfString = new HashMap<String, String>();
        if (tableName != null) {
            for (Field field : DatasetRepository.getInstance().getFields(tableName)) {
                mapOfString.put(field.getName(), "");
            }
        }
        return mapOfString;

    }

    @Override
    public List<String[]> getData(String tableName) {
        this.tableName = tableName;
        List<String[]> result = new LinkedList<String[]>();
        try {
            prepareDataset();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        for (int i = 0; i < this.dataset.getRowCount(); i++) {
            result.add(i, this.dataset.getLine(i));
        }
        return result;
    }

    @Override
    public String[] getHeaders(String tableName) {
        this.tableName = tableName;
        /*if (this.fields.size() == 0) {
            try {
                prepareDataset();
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }  */
        try {
            prepareDataset();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        String[] result = new String[fields.size()];
        int i = 0;
        for (Field field : fields) {
            result[i] = field.getName();
            i++;
        }
        return result;
    }

    @Override
    public List<String> getFieldsForInsert(String currentTable) {
        this.tableName = currentTable;
        List<String> result = new LinkedList<String>();
        for (Field field : this.fields) {
            result.add(field.getName());
        }
        return result;
    }

    @Override
    public void insertData(String[] lines) {
        try {
            prepareDataset();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<Field, String> mapOfValues = new HashMap<Field, String>();
        int i = 0;
        for (Field field : this.fields) {
            mapOfValues.put(field, lines[i]);
            i++;
        }
        try {
            this.dataset.insertData(mapOfValues);
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
}