package ru.susu.crud.components;

public class EntityUpdater extends EntityWorker{
    private String buf;

    public void update(int index, String s) {
        this.buf = this.source.get(index);
        this.source.remove(index);
        this.source.add(s);
    }

    public String getBuf() {
        return buf;
    }
}
