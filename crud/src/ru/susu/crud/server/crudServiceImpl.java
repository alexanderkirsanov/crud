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
            e.printStackTrace();
        }
    }

    @Override
    public String[] getTables() {
        return crudServiceManager.getTables();
    }

    @Override
    public String[] getHeaders(String tableName) throws Exception {
        return crudServiceManager.getHeaders(tableName);
    }

    @Override
    public String[][] insertData(String tableName, String[] lines) throws Exception {
        crudServiceManager.insertData(tableName, lines);
        return selectData(tableName);
    }

    @Override
    public String[][] updateData(String table, int lineNumber, String[] newLine) throws Exception {
        crudServiceManager.updateData(table, lineNumber, newLine);
        return selectData(table);
    }

    @Override
    public String[][] deleteData(String table, int lineNumber) throws Exception {
        crudServiceManager.deleteData(table, lineNumber);
        return selectData(table);
    }

    @Override
    public List<Map<String, String[]>> getEditors(String tableName) throws Exception {
        return crudServiceManager.getEditors(tableName);
    }

    @Override
    public String[][] selectData(String tableName) throws Exception {
        String[] header = getHeaders(tableName);
        String[][] data = crudServiceManager.getData(tableName);
        String[][] result;
        if (header.length != 0 && data.length != 0) {
            result = new String[data.length + 1][header.length];
            for (int i = 0; i < header.length; i++) {
                result[0][i] = header[i];
            }
            System.arraycopy(data, 0, result, 1, data.length);

        } else {
            result = new String[1][header.length];
            for (int i = 0; i < header.length; i++) {
                result[0][i] = header[i];
            }
        }
        return result;
    }


}