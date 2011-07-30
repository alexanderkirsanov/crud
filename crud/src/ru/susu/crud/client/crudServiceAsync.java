package ru.susu.crud.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;
import java.util.Map;

public interface crudServiceAsync {

    void getTables(AsyncCallback<String[]> async);

    void selectData(String table, AsyncCallback<String[][]> async) throws Exception;

    void getHeaders(String tableName, AsyncCallback<String[]> async) throws Exception;

    void insertData(String table, String[] lines, AsyncCallback<String[][]> async) throws Exception;

    void updateData(String table, int lineNumber, String[] newLine, AsyncCallback<String[][]> async) throws Exception;

    void deleteData(String table, int lineNumber, AsyncCallback<String[][]> async) throws Exception;

    void getEditors(String tableName, AsyncCallback<List<Map<String, String[]>>> async) throws Exception;
}
