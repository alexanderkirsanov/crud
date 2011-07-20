package ru.susu.crud.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface crudServiceAsync {

    void getTables(AsyncCallback<List<String>> async);

    void getData(String table, AsyncCallback<List<String[]>> async);

    void getHeaders(String tableName, AsyncCallback<String[]> async);

    void getFieldsForInsert(String currentTable, AsyncCallback<List<String>> async);

    void insertData(String table, String[] lines, AsyncCallback<Void> async);

    void updateData(String table, int lineNumber, String[] newLine, AsyncCallback<Void> async);

    void deleteData(String table, int lineNumber, AsyncCallback<Void> async);
}
