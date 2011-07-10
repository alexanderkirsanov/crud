package ru.susu.crud.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;
import java.util.Map;

@RemoteServiceRelativePath("crudService")
public interface crudService extends RemoteService {

    String getMessage(String msg);

    List<String> getTables();

    void setTable(String tableName);

    Map<String, String> update();

    /**
     * Utility/Convenience class.
     * Use crudService.App.getInstance() to access static instance of crudServiceAsync
     */
    public static class App {
        private static crudServiceAsync ourInstance = GWT.create(crudService.class);

        public static synchronized crudServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
