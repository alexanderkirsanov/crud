package ru.susu.crud.components;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

<<<<<<< HEAD
import ru.susu.crud.Component;
import ru.susu.crud.TextBox;
=======
import ru.susu.crud.components.Component;
import ru.susu.crud.components.TextBox;
>>>>>>> e0811a67b0c8f121e6d13e0eb6131af6dc0ac80a


public class ComponentsTest {
	Component testComponent;
	
	@Before
	public void setUp(){
		testComponent = new Component("1");
	}
	
	@Test
	public void testCreateComponent(){
		assertEquals(testComponent.getName(),"1");
	}
	
	@Test
	public void testGetAllowNullValue(){
		assertTrue(testComponent.getAllowNullValue());
	}
	
	@Test
	public void testSetAllowNullValue(){
		testComponent.setAllowNullValue(false);
		assertFalse(testComponent.getAllowNullValue());
	}
	
	@Test
	public void testCreateTextBox(){
		Component testTextBox = new TextBox("1");
		assertEquals(testTextBox.getName(), "1");
	}

	
<<<<<<< HEAD
}
=======
}
>>>>>>> e0811a67b0c8f121e6d13e0eb6131af6dc0ac80a
