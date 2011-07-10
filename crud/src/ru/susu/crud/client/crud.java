package ru.susu.crud.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.List;
import java.util.Map;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class crud implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final Panel pagePanel = new VerticalPanel();
        final Panel gridPanel = new VerticalPanel();
        crudService.App.getInstance().getTables(new AsyncCallback<List<String>>() {
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
        // RootPanel.get("slot2").add(label);
    }

    private static class VoidAsyncCallback implements AsyncCallback<Void> {

        @Override
        public void onFailure(Throwable caught) {
        }

        @Override
        public void onSuccess(Void result) {
        }
    }

    private static class GridAsyncCallback implements AsyncCallback<Map<String, String>> {
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
    }
}
