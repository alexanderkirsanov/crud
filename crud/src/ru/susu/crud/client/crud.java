package ru.susu.crud.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class crud implements EntryPoint {
    public static final String PATTERN = "yyyy-MM-dd";
    private String currentTable;
    private List<Map<String, String[]>> listOfEditors = new LinkedList<Map<String, String[]>>();

    private VerticalPanel chooseTablePanel = new VerticalPanel();
    private Label chooseTableLabel = new Label("Choose the table");
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

        setTableHeaderText(currentTable.toUpperCase());

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

    private class ViewTablesAsyncCallBack implements AsyncCallback<String[]> {
        private ListBox innerViewCombo;

        public ViewTablesAsyncCallBack(ListBox comboBox) {
            this.innerViewCombo = comboBox;
        }

        @Override
        public void onFailure(Throwable throwable) {
            //Window.alert("!!!");
        }

        @Override
        public void onSuccess(String[] result) {
            for (String tab : result) {
                this.innerViewCombo.addItem(tab);
            }
            innerViewCombo.setSelectedIndex(-1);
        }
    }

    private class ViewDataAsyncCallBack implements AsyncCallback<String[][]> {
        private FlexTable table;

        public ViewDataAsyncCallBack(FlexTable table) {
            this.table = table;
        }

        @Override
        public void onFailure(Throwable throwable) {
            //Window.alert("!");
        }

        @Override
        public void onSuccess(String[][] result) {
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
            table.removeAllRows();
            int column = 0;
            for (String s : result) {
                this.table.setText(0, column, s);
                column++;
            }
        }
    }

    private class ViewFieldsAsyncCallBack implements AsyncCallback<String[]> {
        protected FlexTable table;

        public ViewFieldsAsyncCallBack(FlexTable table) {
            this.table = table;
            crudService.App.getInstance().getEditors(currentTable, new GetEditorsAsyncCallBack());
        }

        @Override
        public void onFailure(Throwable caught) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onSuccess(String[] result) {
            table.removeAllRows();
            int row = 0;
            for (String header : result) {
                table.setText(row, 0, header);
                prepareEditor(row);
                row++;
            }
        }

        protected void prepareEditor(int row){

        }

    }

    private class ViewFieldsForInsertAsyncCallBack extends ViewFieldsAsyncCallBack{

       public ViewFieldsForInsertAsyncCallBack(FlexTable table) {
            super(table);
       }

       protected void prepareEditor(int row){
            EditorsFactory ef = new EditorsFactory();
            table.setWidget(row, 1, ef.getEditor(listOfEditors.get(row).get("type")[0]));
       }

    }

    private class ViewFieldsForUpdateAsyncCallBack extends ViewFieldsAsyncCallBack{
        private String[] updatingLine;

        public ViewFieldsForUpdateAsyncCallBack(FlexTable table, String[] updatingLine) {
            super(table);
            this.updatingLine = updatingLine;
        }

        protected void prepareEditor(int row){
            EditorsFactory ef = new EditorsFactory(updatingLine[row]);
            table.setWidget(row, 1, ef.getEditor(listOfEditors.get(row).get("type")[0]));
        }

    }

    private class GetEditorsAsyncCallBack implements AsyncCallback<List<Map<String, String[]>>> {
        @Override
        public void onFailure(Throwable caught) {

        }

        @Override
        public void onSuccess(List<Map<String, String[]>> result) {
            listOfEditors = result;
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
            EditorsFactory ef = new EditorsFactory();
            for (int i = 0; i < mainTable.getRowCount(); i++){
                lines[i] = ef.getContent(mainTable.getWidget(i, 1));
            }
            crudService.App.getInstance().insertData(currentTable, lines, new VoidAsyncCallback());
            baseView();
        }
    }

    private class UpdateButtonClickHandler implements ClickHandler {
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
            crudService.App.getInstance().getHeaders(currentTable, new ViewFieldsForUpdateAsyncCallBack(mainTable, updatingLine));

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
            crudService.App.getInstance().getHeaders(currentTable, new ViewFieldsForInsertAsyncCallBack(mainTable));
            Button insertButton = new Button("Add to the table");

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
            EditorsFactory ef = new EditorsFactory();
            for (int i = 0; i < mainTable.getRowCount(); i++){
                lines[i] = ef.getContent(mainTable.getWidget(i, 1));
            }
            crudService.App.getInstance().updateData(currentTable, this.lineToUpdate, lines, new VoidAsyncCallback());
            baseView();
        }
    }

    private class EditorsFactory {
        private String editorValue;

        public EditorsFactory() {
            this.editorValue = null;
        }

        public EditorsFactory(String value){
            this.editorValue = value;
        }

        public Widget getEditor(String type){
            Widget result = new Widget();

            if (type.equals("TextEditor")){
                TextBox tb = new TextBox();
                if (this.editorValue!=null) {
                tb.setText(editorValue);
                }
                result = tb;
            }
            if (type.equals("DateEditor")){
                DateBox db = new DateBox();
                DateBox.DefaultFormat df = new DateBox.DefaultFormat(DateTimeFormat.getFormat(PATTERN));
                db.setFormat(df);
                if (this.editorValue==null) {
                    db.setValue(new Date(System.currentTimeMillis()));
                }
                else {
                    DateTimeFormat dtf = DateTimeFormat.getFormat(PATTERN);
                    db.setValue(dtf.parse(this.editorValue));
                }


                /*DateBox.Format df = new DateBox.DefaultFormat();
                Date date = df.parse(db, editorValue, false);

                db.setValue(date);*/
                result = db;

            }

            return result;


            /*TextBox tb = new TextBox();
            if (this.editorType !=null) {
                tb.setText(editorType);
            }
            return tb;*/
        }

        public String getContent(Widget widget) {
            String result = null;
            if (widget instanceof TextBox) {
                result = ((TextBox)widget).getText();
            }
            if (widget instanceof DateBox) {
                DateTimeFormat df = DateTimeFormat.getFormat(PATTERN);
                result = df.format(((DateBox)widget).getValue());
            }
            return result;
        }
    }
}
