package ru.susu.crud.components;

public class Component {

    private String name;
    private boolean allowNullValue;

    public Component(String name) {
        this.name = name;
        this.allowNullValue = true;
    }

    public String getName() {
        return this.name;
    }

    public boolean getAllowNullValue() {
        return this.allowNullValue;
    }

    public void setAllowNullValue(boolean allowNullValue) {
        if (this.allowNullValue != allowNullValue) {
            this.allowNullValue = allowNullValue;
        }
    }

}
