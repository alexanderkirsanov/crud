package ru.susu.crud.editor;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DateEditorTest {
    @Test
    public void getDefinitionTest() throws Exception {
        DateEditor dateEditor = new DateEditor();
        Map<String, String[]> definition = dateEditor.getDefinition();
        assertEquals("DateEditor", definition.get("type")[0]);
    }
}
