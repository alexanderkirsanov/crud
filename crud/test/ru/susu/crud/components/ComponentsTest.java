package ru.susu.crud.components;

import org.junit.Before;
import org.junit.Test;
import ru.susu.crud.components.editors.TextBox;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class ComponentsTest {
    Component testComponent;

    @Before
    public void setUp() {
        testComponent = new Component("1");
    }

    @Test
    public void testCreateComponent() {
        assertEquals(testComponent.getName(), "1");
    }

    @Test
    public void testGetAllowNullValue() {
        assertTrue(testComponent.getAllowNullValue());
    }

    @Test
    public void testSetAllowNullValue() {
        testComponent.setAllowNullValue(false);
        assertFalse(testComponent.getAllowNullValue());
    }

    @Test
    public void testCreateTextBox() {
        Component testTextBox = new TextBox("1");
        assertEquals(testTextBox.getName(), "1");
    }

    @Test
    public void EntityFinderTest(){
        EntityFinder testFinder = new EntityFinder();
        ArrayList<String> testList = new ArrayList<String>();
        testFinder.setSource(testList);
        testList.add("1111");
        String elem1 = "aaaaa";
        String elem2 = "bbbaadf";

        testList.add(elem1);
        testList.add(elem2);
        testList.add("456");
        testList.add("");

        ArrayList<String> findList = new ArrayList<String>();
        findList.add(elem1);
        findList.add(elem2);

        assertEquals(testFinder.find("a"), findList);
        System.out.println(testFinder.find("a"));
    }


}