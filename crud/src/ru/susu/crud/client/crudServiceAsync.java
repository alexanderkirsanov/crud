package ru.susu.crud.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;
import java.util.Map;

public interface crudServiceAsync {

    void getTables(AsyncCallback<List<String>> async);

    void setTable(String tableName, AsyncCallback<Void> async);

    void update(AsyncCallback<Map<String, String>> async);

    void getData(String s, AsyncCallback<List<String[]>> async);

    void getHeaders(String tableName, AsyncCallback<String[]> async);

    void getFieldsForInsert(String currentTable, AsyncCallback<List<String>> async);

    void insertData(String[] lines, AsyncCallback<Void> async);
}
