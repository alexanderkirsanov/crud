package ru.susu.crud.components.editors;

public class EditorsConf {
    private static EditorsConf instance;

    public static EditorsConf getInstance() {
        if (instance == null) {
            instance = new EditorsConf();
        }
        return instance;
    }

    public final String dateWithTimePattern = "yyyy-mm-dd HH:mm:ss";
    public final String dateWithoutTimePattern = "yyyy-mm-dd";
}
