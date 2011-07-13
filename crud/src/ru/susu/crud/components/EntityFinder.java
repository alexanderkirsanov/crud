package ru.susu.crud.components;

import java.util.ArrayList;

public class EntityFinder extends EntityWorker{

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
