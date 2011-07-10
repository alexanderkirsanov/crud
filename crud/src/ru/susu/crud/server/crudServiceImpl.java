package ru.susu.crud.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.susu.crud.client.crudService;
import ru.susu.crud.xml.Column;
import ru.susu.crud.xml.TableDefinition;
import ru.susu.crud.xml.XMLReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class crudServiceImpl extends RemoteServiceServlet implements crudService {
    private String tableName;
    private Map<String, TableDefinition> tableDefinitionMap = new HashMap<String, TableDefinition>();

    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }

    @Override
    public List<String> getTables() {
        XMLReader xmlReader = new XMLReader("table.xml");
        List<String> tables = new ArrayList<String>();
        tableDefinitionMap = xmlReader.getTables();
        for (String table : tableDefinitionMap.keySet()) {
            tables.add(table);
        }
        return tables;

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
}