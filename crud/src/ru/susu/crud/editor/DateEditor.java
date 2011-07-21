package ru.susu.crud.editor;

import java.util.HashMap;
import java.util.Map;

public class DateEditor implements Editor {
    @Override
    public Map<String, String[]> getDefinition() {
        Map<String, String[]> definition = new HashMap<String, String[]>();
        definition.put("type", new String[]{this.getClass().getSimpleName()});
        return definition;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass());
    }
}
