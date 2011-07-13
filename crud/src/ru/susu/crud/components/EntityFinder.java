package ru.susu.crud.components;

import java.util.ArrayList;

public class EntityFinder {
    private ArrayList<String> source;

    public void setSource(ArrayList<String> source) {
        this.source = source;
    }

    public ArrayList<String> find(String s) {
        ArrayList<String> result = new ArrayList<String>();
        for (String str : this.source) {
            if (str.indexOf(s) != -1) {
                result.add(str);
            }
        }
        return result;
    }
}
