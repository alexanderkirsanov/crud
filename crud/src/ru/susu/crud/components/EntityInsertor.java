package ru.susu.crud.components;

public class EntityInsertor  extends EntityWorker {

    public void insert(String... s) {
        for (String str : s){
            this.source.add(str);
        }
    }
}
