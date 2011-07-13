package ru.susu.crud.components;

import org.junit.Before;
import org.junit.Test;
import ru.susu.crud.components.editors.TextBox;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class ComponentsTest {
    Component testComponent;
    ArrayList<String> testList;
    String elem1;
    String elem2;

    @Before
    public void setUp() {
        testComponent = new Component("1");
        testList = new ArrayList<String>();
            testList.add("1111");
            elem1 = "aaaaa";
            elem2 = "bbbaadf";
            testList.add(elem1);
            testList.add(elem2);
            testList.add("456");
            testList.add("");

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
        testFinder.setSource(testList);
        ArrayList<String> findList = new ArrayList<String>();
        findList.add(elem1);
        findList.add(elem2);
        assertEquals(testFinder.find("a"), findList);
    }

    @Test
    public void EntityInsertorTest() {
        EntityInsertor testInsertor = new EntityInsertor();
        testInsertor.setSource(testList);
        String innerElem1 = "1233";
        String innerElem2 = "asdf";
        String innerElem3 = "0000";
        testInsertor.insert(innerElem1, innerElem2, innerElem3);
        assertTrue(testInsertor.contains(innerElem1));
        assertTrue(testInsertor.contains(innerElem2));
        assertTrue(testInsertor.contains(innerElem3));
    }


}