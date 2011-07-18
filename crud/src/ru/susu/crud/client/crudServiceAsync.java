package ru.susu.crud.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface crudServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);

    void getTables(AsyncCallback<List<String>> async);

    void setTable(String tableName, AsyncCallback<Void> async);

    void update(AsyncCallback<Map<String, String>> async);

    void find(String s, AsyncCallback<ArrayList<String>> async);

    void getStrings(AsyncCallback<ArrayList<String>> async);

    void getHeaders(String s, AsyncCallback<List<String>> async);
}
