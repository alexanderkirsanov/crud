package ru.susu.crud.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("crudService")
public interface crudService extends RemoteService {

    List<String> getTables();

    List<String[]> selectData(String table);

    String[] getHeaders(String tableName) ;

    List<String> getFieldsForInsert(String currentTable);

    void insertData(String table, String[] lines);

    void updateData(String table, int lineNumber, String[] newLine);

    void deleteData(String table, int lineNumber);


    /**
     * Utility/Convenience class.
     * Use crudService.App.getInstance() to access static instance of crudServiceAsync
     */
    public static class App {
        private static crudServiceAsync ourInstance = (crudServiceAsync) GWT.create(crudService.class);

        public static synchronized crudServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
