package ru.susu.crud.components;

public class EntityInsertor  extends EntityWorker {

    public void insert(String s) {
        this.source.add(s);
    }
}
