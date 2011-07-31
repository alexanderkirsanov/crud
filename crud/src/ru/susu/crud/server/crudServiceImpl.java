package ru.susu.crud.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.susu.crud.client.crudService;
import ru.susu.crud.configurator.PageConfigurator;

import java.sql.SQLException;

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
        try {
            return crudServiceManager.getHeaders(tableName);
        } catch (SQLException e) {
            throw new Exception(e.toString());
        }
    }

    @Override
    public String[][] insertData(String tableName, String[] lines) throws Exception {
        try {
            crudServiceManager.insertData(tableName, lines);
            return selectData(tableName);
        } catch (SQLException e) {
            throw new Exception(e.toString());
        }
    }

    @Override
    public String[][] updateData(String table, int lineNumber, String[] newLine) throws Exception {
        try {
            crudServiceManager.updateData(table, lineNumber, newLine);
            return selectData(table);
        } catch (SQLException e) {
            throw new Exception(e.toString());
        }
    }

    @Override
    public String[][] deleteData(String table, int lineNumber) throws Exception {
        try {
            crudServiceManager.deleteData(table, lineNumber);
            return selectData(table);
        } catch (SQLException e) {
            throw new Exception(e.toString());
        }
    }

    @Override
    public String[][] getEditors(String tableName) throws Exception {
        try {
            return crudServiceManager.getEditors(tableName);
        } catch (SQLException e) {
            throw new Exception(e.toString());
        }
    }

    @Override
    public String[][] selectData(String tableName) throws Exception {
        try {
            String[] header = getHeaders(tableName);
            String[][] data = crudServiceManager.getData(tableName);
            String[][] result;
            if (header.length != 0 && data.length != 0) {
                result = new String[data.length + 1][header.length];
                System.arraycopy(header, 0, result[0], 0, header.length);
                System.arraycopy(data, 0, result, 1, data.length);

            } else {
                result = new String[1][header.length];
                System.arraycopy(header, 0, result[0], 0, header.length);
            }
            return result;
        } catch (SQLException e) {
            throw new Exception(e.toString());
        }
    }


}