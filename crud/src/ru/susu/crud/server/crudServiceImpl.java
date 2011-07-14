package ru.susu.crud.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.susu.crud.client.crudService;
import ru.susu.crud.components.EntityFinder;
import ru.susu.crud.xml.Column;
import ru.susu.crud.xml.TableDefinition;
import ru.susu.crud.xml.XMLReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class crudServiceImpl extends RemoteServiceServlet implements crudService {
    EntityFinder finder = new EntityFinder();
    private ArrayList<String> sourceForView;
    private String tableName;
    private Map<String, TableDefinition> tableDefinitionMap = new HashMap<String, TableDefinition>();

    public crudServiceImpl(){
        sourceForView = new ArrayList<String>();
        sourceForView.add("13456");
        sourceForView.add("aaaabbbb");
        sourceForView.add("33333");
        sourceForView.add("xfdg");
        sourceForView.add("asderx");
        sourceForView.add("444443");
        this.finder.setSource(sourceForView);
    }

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

    @Override
    public ArrayList<String> find(String s) {
        return this.finder.find(s);
    }

    @Override
    public ArrayList<String> getStrings() {
        return this.sourceForView;
    }
}