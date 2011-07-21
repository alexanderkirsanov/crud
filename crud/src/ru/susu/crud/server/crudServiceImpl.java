package ru.susu.crud.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.susu.crud.client.crudService;
import ru.susu.crud.configurator.PageConfigurator;

import java.util.List;
import java.util.Map;

public class crudServiceImpl extends RemoteServiceServlet implements crudService {
    private CrudServiceManager crudServiceManager;

    public crudServiceImpl() {
        try {
            crudServiceManager = new CrudServiceManager();
            new PageConfigurator(crudServiceManager).configure();
        } catch (Exception e) {
            System.out.println("die");
        }
    }

    @Override
    public List<String> getTables() {
        return crudServiceManager.getTables();
    }

    @Override
    public List<String[]> selectData(String tableName) {
        return crudServiceManager.getData(tableName);
    }

    @Override
    public String[] getHeaders(String tableName) {
        return crudServiceManager.getHeaders(tableName);
    }

    @Override
    public List<String> getFieldsForInsert(String currentTable) {
        return crudServiceManager.getFieldsForInsert(currentTable);
    }

    @Override
    public void insertData(String tableName, String[] lines) {
        crudServiceManager.insertData(tableName, lines);
    }

    @Override
    public void updateData(String table, int lineNumber, String[] newLine) {
        crudServiceManager.updateData(table, lineNumber, newLine);
    }

    @Override
    public void deleteData(String table, int lineNumber) {
        crudServiceManager.deleteData(table, lineNumber);
    }

    @Override
    public List<Map<String, String[]>> getEditors(String tableName) {
        return crudServiceManager.getEditors(tableName);
    }


}