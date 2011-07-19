package ru.susu.crud.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.susu.crud.client.crudService;
import ru.susu.crud.components.EntityFinder;
import ru.susu.crud.database.ConnectionProperties;
import ru.susu.crud.database.DatasetFactory;
import ru.susu.crud.database.connection.ConnectionManager;
import ru.susu.crud.database.dataset.Dataset;
import ru.susu.crud.database.dataset.Field;
import ru.susu.crud.xml.Column;
import ru.susu.crud.xml.TableDefinition;
import ru.susu.crud.xml.XMLReader;

import java.util.*;

public class crudServiceImpl extends RemoteServiceServlet implements crudService {
    EntityFinder finder = new EntityFinder();
    private ArrayList<String> sourceForView;
    private String tableName;
    private Map<String, TableDefinition> tableDefinitionMap = new HashMap<String, TableDefinition>();
    private Dataset dataset;
    private List<Field> fields;
    private Map<String, String[]> mapOfData = new HashMap<String, String[]>();

    public crudServiceImpl() {

    }

    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }

    @Override
    public List<String> getTables() {
        try {
            prepareDataset();
        } catch (Exception e) {

        }
        List<String> tables = new ArrayList<String>();
        for (String table : tableDefinitionMap.keySet()) {
            tables.add(table);
        }
        return tables;
    }

    private void prepareDataset() throws Exception {
        ConnectionProperties connectionProperties = new ConnectionProperties("localhost", "test", "dem", "s1234s", 3306);
        ConnectionManager connectionManager = new ConnectionManager(connectionProperties);
        XMLReader xmlReader = new XMLReader("table.xml");
        tableDefinitionMap = xmlReader.getTables();
        DatasetFactory datasetFactory = null;
        try {
            datasetFactory = new DatasetFactory(xmlReader.getTables(), connectionManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.dataset = datasetFactory.getDataset(this.tableName);
        this.fields = datasetFactory.getFields(this.tableName);
        this.dataset.selectData();
    }

    @Override
    public void setTable(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public Map<String, String> update() {
        Map<String, String> mapOfString = new HashMap<String, String>();
        if (tableDefinitionMap != null && tableName != null) {
            TableDefinition tableDefinition = tableDefinitionMap.get(tableName);
            for (Column column : tableDefinition.getColumns()) {
                mapOfString.put(column.getName(), "");
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
}