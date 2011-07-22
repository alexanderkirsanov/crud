package ru.susu.crud.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class crud implements EntryPoint {
    private String currentTable;

    private VerticalPanel chooseTablePanel = new VerticalPanel();
    private Label chooseTableLabel = new Label("Choose the mainTable");
    private ListBox chooseTableComboBox = new ListBox(false);

    private VerticalPanel subMainPanel = new VerticalPanel();
    private Label tableHeader = new Label();
    private final FlexTable mainTable = new FlexTable();
    private Button addEntryButton = new Button("Add");

    public String getTableHeaderText() {
        return tableHeader.getText();
    }

    public void setTableHeaderText(String text) {
        this.tableHeader.setText(text);
    }


    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        chooseTablePanel.add(chooseTableLabel);
        chooseTablePanel.add(chooseTableComboBox);

        chooseTableComboBox.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent changeEvent) {
                currentTable = chooseTableComboBox.getItemText(chooseTableComboBox.getSelectedIndex());
                try {
                    baseView();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        addEntryButton.addClickHandler(new AddEntryClickHandler());

        crudService.App.getInstance().getTables(new ViewTablesAsyncCallBack(chooseTableComboBox));
        currentTable = "test";

        baseView();

        RootPanel.get("root").add(subMainPanel);
        RootPanel.get("tables_list").add(chooseTablePanel);
    }

    private void baseView() {
        subMainPanel.clear();

        crudService.App.getInstance().getHeaders(currentTable, new ViewHeadersAsyncCallBack(mainTable));
        crudService.App.getInstance().selectData(currentTable, new ViewDataAsyncCallBack(mainTable));

        mainTable.setBorderWidth(1);
        mainTable.setCellSpacing(0);
        mainTable.setCellPadding(4);

        subMainPanel.add(tableHeader);
        subMainPanel.add(mainTable);
        subMainPanel.add(addEntryButton);
    }


    private static class VoidAsyncCallback implements AsyncCallback<Void> {

        @Override
        public void onFailure(Throwable caught) {
            Window.alert("!!!");
        }

        @Override
        public void onSuccess(Void result) {
        }
    }

    private class ViewTablesAsyncCallBack implements AsyncCallback<List<String>> {
        private ListBox innerViewCombo;

        public ViewTablesAsyncCallBack(ListBox comboBox) {
            this.innerViewCombo = comboBox;
        }

        @Override
        public void onFailure(Throwable throwable) {
            //Window.alert("!!!");
        }

        @Override
        public void onSuccess(List<String> result) {
            for (String tab : result) {
                this.innerViewCombo.addItem(tab);
            }
            innerViewCombo.setSelectedIndex(-1);
        }
    }

    private class ViewDataAsyncCallBack implements AsyncCallback<List<String[]>> {
        private FlexTable table;

        public ViewDataAsyncCallBack(FlexTable table) {
            this.table = table;
        }

        @Override
        public void onFailure(Throwable throwable) {
            //Window.alert("!");
        }

        @Override
        public void onSuccess(List<String[]> result) {
            int i = 0;
            for (String[] line : result){
                for (int j = 0; j < line.length; j++){
                    this.table.setText(i+2,j,line[j]);
                }

                Button deleteButton = new Button("Delete");
                deleteButton.addClickHandler(new DeleteButtonClickHandler(i));
                this.table.setWidget(i + 2, line.length, deleteButton);

                Button updateButton = new Button("Update");
                updateButton.addClickHandler(new UpdateButtonClickHandler(i,line));
                this.table.setWidget(i+2,line.length+1,updateButton);

                i++;
            }
        }
    }

    private class ViewFieldsForInsertAsyncCallBack implements AsyncCallback<List<String>> {
        private FlexTable table;

        public ViewFieldsForInsertAsyncCallBack(FlexTable table) {
            this.table = table;
        }

        @Override
        public void onFailure(Throwable throwable) {

        }

        @Override
        public void onSuccess(List<String> result) {
            table.removeAllRows();
            int row = 0;
            for (String header : result) {
                table.setText(row, 0, header);
                table.setWidget(row, 1, new TextBox());
                row++;
            }
        }
    }

    private class ViewHeadersAsyncCallBack implements AsyncCallback<String[]> {
        private FlexTable table;

        public ViewHeadersAsyncCallBack(FlexTable table) {
            this.table = table;
        }

        @Override
        public void onFailure(Throwable throwable) {
        }

        @Override
        public void onSuccess(String[] result) {
            setTableHeaderText(currentTable.toUpperCase());
            table.removeAllRows();
            int column = 0;
            for (String s : result) {
                this.table.setText(0, column, s);
                column++;
            }
        }
    }

    private class ViewFieldsForUpdateAsyncCallBack implements AsyncCallback<List<String>> {
        private FlexTable table;
        private String[] updatingLine;

        public ViewFieldsForUpdateAsyncCallBack(FlexTable table, String[] updatingLine) {
            this.table = table;
            this.updatingLine = updatingLine;
        }

        @Override
        public void onFailure(Throwable caught) {

        }

        @Override
        public void onSuccess(List<String> result) {
            table.removeAllRows();
            int row = 0;
            for (String header : result) {
                table.setText(row, 0, header);
                TextBox tb = new TextBox();
                tb.setText(updatingLine[row]);
                table.setWidget(row, 1, tb);
                row++;
            }
        }
    }

    private class DeleteButtonClickHandler implements ClickHandler {

        private int lineToDelete;

        public DeleteButtonClickHandler(int lineToDelete){
            this.lineToDelete = lineToDelete;
        }

        @Override
        public void onClick(ClickEvent event) {
            crudService.App.getInstance().deleteData(currentTable, lineToDelete, new VoidAsyncCallback());
            baseView();
        }
    }

    private class InsertButtonClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
            String[] lines = new String[mainTable.getRowCount()];
            for (int i = 0; i < mainTable.getRowCount(); i++){
                lines[i] = ((TextBox) mainTable.getWidget(i, 1)).getText();
            }
            crudService.App.getInstance().insertData(currentTable, lines, new VoidAsyncCallback());
            baseView();
        }
    }

    private class UpdateButtonClickHandler implements ClickHandler {
        //обязательно финальные поля, т.к. ты их прокидываешь во внутренний анонимный класс : )
        private final int lineToUpdate;
        private final String[] updatingLine;

        public UpdateButtonClickHandler(int lineToUpdate, String[] updatingLine) {
            this.lineToUpdate = lineToUpdate;
            this.updatingLine = updatingLine;
        }

        @Override
        public void onClick(ClickEvent event) {
            subMainPanel.clear();
            setTableHeaderText("Update the entry");
            crudService.App.getInstance().getFieldsForInsert(currentTable, new ViewFieldsForUpdateAsyncCallBack(mainTable, updatingLine));

            Button updateEntryButton = new Button("Update");

            updateEntryButton.addClickHandler(new UpdateEntryButtonClickHandler(this.lineToUpdate));

            subMainPanel.add(tableHeader);
            subMainPanel.add(mainTable);
            subMainPanel.add(updateEntryButton);
        }
    }

    private class AddEntryClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
            subMainPanel.clear();
            setTableHeaderText("Add the entry");
            crudService.App.getInstance().getFieldsForInsert(currentTable, new ViewFieldsForInsertAsyncCallBack(mainTable));
            Button insertButton = new Button("Add to mainTable");

            insertButton.addClickHandler(new InsertButtonClickHandler());

            subMainPanel.add(tableHeader);
            subMainPanel.add(mainTable);
            subMainPanel.add(insertButton);
        }
    }
    private class UpdateEntryButtonClickHandler implements ClickHandler {
        private int lineToUpdate;

        public UpdateEntryButtonClickHandler(int lineToUpdate) {
            this.lineToUpdate = lineToUpdate;
        }

        @Override
        public void onClick(ClickEvent event) {
            String[] lines = new String[mainTable.getRowCount()];
            for (int i = 0; i < mainTable.getRowCount(); i++){
                lines[i] = ((TextBox) mainTable.getWidget(i, 1)).getText();
            }
            crudService.App.getInstance().updateData(currentTable, this.lineToUpdate, lines, new VoidAsyncCallback());
            baseView();
        }
    }
}
