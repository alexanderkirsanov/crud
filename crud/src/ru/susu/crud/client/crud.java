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

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class crud implements EntryPoint {
    public static final String FIND_ITEM = "Поиск";
    public static final String ADD_ITEM = "Добавление";
    public static final String UPDATE_ITEM = "Редактирование";

    private VerticalPanel mainPanel = new VerticalPanel();
    private HorizontalPanel comboBoxPanel = new HorizontalPanel();
    private Label comboBoxLabel = new Label();
    private ListBox comboBox = new ListBox(false);
    private HorizontalPanel insertPanel = new HorizontalPanel();
    private final FlexTable table = new FlexTable();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

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
                crudService.App.getInstance().find(insertTextBox.getText(), new FindAsyncCallBack(table));
            }
        });

        comboBox.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent changeEvent) {
                insertPanel.clear();
                if (comboBox.getItemText(comboBox.getSelectedIndex()).equals(FIND_ITEM))  {
                     Label insertLabel = new Label("Символы");
                     final TextBox insertTextBox = new TextBox();
                     Button insertButton = new Button("Поиск");

                     insertPanel.add(insertLabel);
                     insertPanel.add(insertTextBox);
                     insertPanel.add(insertButton);

                     insertButton.addClickHandler(new ClickHandler() {
                         @Override
                         public void onClick(ClickEvent clickEvent) {
                             crudService.App.getInstance().find(insertTextBox.getText(), new FindAsyncCallBack(table));
                         }
                     });
                }
                if (comboBox.getItemText(comboBox.getSelectedIndex()).equals(ADD_ITEM)) {
                    final Label insertLabel = new Label("Строка");
                    final TextBox insertTextBox = new TextBox();
                    Button addButton = new Button("Добавить");
                    insertPanel.add(insertLabel);
                    insertPanel.add(insertTextBox);
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

        crudService.App.getInstance().getStrings(new ViewAsyncCallback(table));

        mainPanel.add(comboBoxPanel);
        mainPanel.add(insertPanel);
        mainPanel.add(table);

        RootPanel.get("root").add(mainPanel);

        /*crudService.App.getInstance().getTables(new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<String> result) {
                for (final String s : result) {
                    Label label = new Label(s);

                    label.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent event) {
                            crudService.App.getInstance().setTable(s, new VoidAsyncCallback());
                            crudService.App.getInstance().update(new GridAsyncCallback(gridPanel));
                        }
                    });
                    pagePanel.add(label);
                }
            }
        });


        RootPanel.get("page_navigator").add(pagePanel);
        RootPanel.get("grid").add(gridPanel);
        // RootPanel.get("slot2").add(label); */
    }

    private static class VoidAsyncCallback implements AsyncCallback<Void> {

        @Override
        public void onFailure(Throwable caught) {
        }

        @Override
        public void onSuccess(Void result) {
        }
    }

    /*private static class GridAsyncCallback implements AsyncCallback<Map<String, String>> {
        private Panel panel;

        public GridAsyncCallback(Panel panel) {
            this.panel = panel;
        }

        @Override
        public void onFailure(Throwable caught) {
        }

        @Override
        public void onSuccess(Map<String, String> result) {
            panel.clear();
            for (String columnName : result.keySet()) {
                panel.add(new Label(columnName));
            }
        }
    }    */

    private static class FindAsyncCallBack implements AsyncCallback<ArrayList<String>> {
        private FlexTable table;

        public FindAsyncCallBack(FlexTable table) {
            this.table = table;
        }

        @Override
        public void onFailure(Throwable throwable) {
            Window.alert("!!!!!");
        }

        @Override
        public void onSuccess(ArrayList<String> result) {
            table.removeAllRows();
            for (String s : result) {
                this.table.setText(this.table.getRowCount(),0,s);
            }
            //Window.alert(result.toString());
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
                this.table.setText(this.table.getRowCount(),0,s);
            }
        }
    }

}
