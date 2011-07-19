package ru.susu.crud.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class crud implements EntryPoint {
    public static final String FIND_ITEM = "Поиск";
    public static final String ADD_ITEM = "Добавление";
    public static final String UPDATE_ITEM = "Редактирование";

    private VerticalPanel chooseTablePanel = new VerticalPanel();
    private Label chooseTableLabel = new Label("Choose the table");
    private ListBox tablesComboBox = new ListBox(false);
    private String currentTable;

    private VerticalPanel subMainPanel = new VerticalPanel();
    private HorizontalPanel comboBoxPanel = new HorizontalPanel();
    private Label comboBoxLabel = new Label();
    private ListBox comboBox = new ListBox(false);
    private HorizontalPanel insertPanel = new HorizontalPanel();
    private final FlexTable table = new FlexTable();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        chooseTablePanel.add(chooseTableLabel);
        chooseTablePanel.add(tablesComboBox);

        comboBoxLabel.setText("Действие");
        comboBox.addItem(FIND_ITEM);
        comboBox.addItem(ADD_ITEM);
        comboBox.addItem(UPDATE_ITEM);
        comboBoxPanel.add(comboBoxLabel);
        comboBoxPanel.add(comboBox);

        //отрефакторить это ужасное дублирование
        Label insertLabel = new Label("Символы");
        final TextBox insertTextBox = new TextBox();
        Button insertButton = new Button("Поиск");

        insertPanel.add(insertLabel);
        insertPanel.add(insertTextBox);
        insertPanel.add(insertButton);

        insertButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                //crudService.App.getInstance().find(insertTextBox.getText(), new FindAsyncCallBack(table));
            }
        });

        comboBox.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent changeEvent) {
                insertPanel.clear();
                if (comboBox.getItemText(comboBox.getSelectedIndex()).equals(FIND_ITEM)) {
                    Label insertLabel = new Label("Символы");
                    final TextBox insertTextBox = new TextBox();
                    Button insertButton = new Button("Поиск");

                    insertPanel.add(insertLabel);
                    insertPanel.add(insertTextBox);
                    insertPanel.add(insertButton);

                    insertButton.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent clickEvent) {
                            //crudService.App.getInstance().find(insertTextBox.getText(), new FindAsyncCallBack(table));
                        }
                    });
                }
                if (comboBox.getItemText(comboBox.getSelectedIndex()).equals(ADD_ITEM)) {
                    final Label insertLabel = new Label("Строка");
                    final TextBox insertTextBox = new TextBox();
                    final FlexTable flexTable = new FlexTable();

                    Button addButton = new Button("Добавить");
                    insertPanel.add(insertLabel);
                    crudService.App.getInstance().getHeaders(currentTable, new ViewFieldAsyncCallBack(flexTable));
                    insertPanel.add(flexTable);
                    insertPanel.add(addButton);
                }
                if (comboBox.getItemText(comboBox.getSelectedIndex()).equals(UPDATE_ITEM)) {
                    final Label insertLabel = new Label("Изменение");
                    final TextBox insertTextBox = new TextBox();
                    Button updateButton = new Button("Изменить");
                    insertPanel.add(insertLabel);
                    insertPanel.add(insertTextBox);
                    insertPanel.add(updateButton);
                }
            }
        });

        tablesComboBox.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent changeEvent) {
                try {
                    crudService.App.getInstance().getHeaders(tablesComboBox.getItemText(tablesComboBox.getSelectedIndex()), new ViewHeadersAsyncCallBack(table));
                    crudService.App.getInstance().getData(tablesComboBox.getItemText(tablesComboBox.getSelectedIndex()), new ViewDataAsyncCallBack(table));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //crudService.App.getInstance().getStrings(new ViewAsyncCallback(table));

        //try {
        crudService.App.getInstance().getTables(new ViewTablesAsyncCallBack(tablesComboBox));
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        //crudService.App.getInstance().setTable("students", new VoidAsyncCallback());
        currentTable = "students";
        //try {
        crudService.App.getInstance().getHeaders(currentTable, new ViewHeadersAsyncCallBack(table));
        //crudService.App.getInstance().getData(currentTable, new ViewDataAsyncCallBack(table));
        //} catch (Exception e) {
        //  e.printStackTrace();
        //}

        table.setBorderWidth(1);

        subMainPanel.add(comboBoxPanel);
        subMainPanel.add(insertPanel);
        subMainPanel.add(table);

        RootPanel.get("root").add(subMainPanel);
        RootPanel.get("tables_list").add(chooseTablePanel);
    }


    private static class VoidAsyncCallback implements AsyncCallback<Void> {

        @Override
        public void onFailure(Throwable caught) {
        }

        @Override
        public void onSuccess(Void result) {
        }
    }

    private static class FindAsyncCallBack implements AsyncCallback<ArrayList<String>> {
        private FlexTable table;

        public FindAsyncCallBack(FlexTable table) {
            this.table = table;
        }

        @Override
        public void onFailure(Throwable throwable) {
        }

        @Override
        public void onSuccess(ArrayList<String> result) {
            this.table.removeAllRows();
            int i = 0;
            for (String s : result) {
                this.table.setText(i, i, s);
                i++;
            }
        }
    }

    private static class ViewAsyncCallback implements AsyncCallback<ArrayList<String>> {

        private FlexTable table;

        public ViewAsyncCallback(FlexTable table) {
            this.table = table;
        }

        @Override
        public void onFailure(Throwable caught) {
        }

        @Override
        public void onSuccess(ArrayList<String> result) {
            for (String s : result) {
                this.table.setText(this.table.getRowCount(), 0, s);
            }
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
            //currentTable = this.innerViewCombo.getItemText(this.innerViewCombo.getSelectedIndex());
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
            Window.alert("!");
        }

        @Override
        public void onSuccess(List<String[]> result) {
            int i = 0;
            while (i < result.size()){
                String[] line = result.get(i);
                for (int j = 0; j < line.length; j++){
                    this.table.setText(i+2,j,line[j]);
                }
                i++;
            }
        }
    }

    private class ViewFieldAsyncCallBack implements AsyncCallback<String[]> {
        private HTMLTable table;

        public ViewFieldAsyncCallBack(HTMLTable table) {
            this.table = table;
        }

        @Override
        public void onFailure(Throwable throwable) {

        }

        @Override
        public void onSuccess(String[] result) {
            table.clear();
            int column = 0;
            for (String header : result) {

                table.setText(0, column, header);
                table.setWidget(1, column, new TextBox());
                column++;
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
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onSuccess(String[] result) {
            table.removeAllRows();
            int column = 0;
            for (String s : result) {
                this.table.setText(0,column,s);
                column++;
            }
        }
    }
}
