package ru.susu.crud.components.editors;

public class DateTimeEdit extends CustomEditor {
    private boolean showsTime;
    private String format;

    public DateTimeEdit(String name, boolean showsTime) {
        super(name);
        this.showsTime = showsTime;
        if (showsTime == false) {
            this.format = EditorsConf.getInstance().dateWithoutTimePattern;
        } else {
            this.format = EditorsConf.getInstance().dateWithTimePattern;
        }
    }

    public boolean isShowsTime() {
        return showsTime;
    }

    public void setShowsTime(boolean showsTime) {
        this.showsTime = showsTime;
    }

    public String getFormat() {
        return format;
    }
}
