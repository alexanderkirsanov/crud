package ru.susu.crud.components;

import java.util.ArrayList;

public class EntityWorker {
    protected ArrayList<String> source;

    public void setSource(ArrayList<String> source) {
        this.source = source;
    }

    public boolean contains(String s) {
        return this.source.contains(s);
    }
}
