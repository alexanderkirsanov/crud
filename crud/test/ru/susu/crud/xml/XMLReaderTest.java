package ru.susu.crud.xml;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XMLReaderTest {

    private XMLReader xmlReader;

    @Before
    public void setUp(){
        xmlReader = new XMLReader("table.xml");
    }

    @Test
    public void readDefenitionTest(){
        assertEquals(2, xmlReader.getTables().size());
    }
}
