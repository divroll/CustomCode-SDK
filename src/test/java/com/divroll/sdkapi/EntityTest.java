package com.divroll.sdkapi;

import com.divroll.core.CannotCastValueException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class EntityTest {

    @Test
    public void testEntityConstructorWithTypeAndProperties() throws CannotCastValueException {
        // Setup
        Map<String, EntityProperty> properties = new HashMap<>();
        properties.put("name", new StringProperty("Test Entity"));
        
        // Execution
        Entity entity = new Entity("TestType", properties);
        
        // Assertions
        assertNotNull(entity);
        assertEquals("Test Entity", entity.get("name").getValue());
    }

    @Test(expected = CannotCastValueException.class)
    public void testEntityConstructorWithInvalidType() throws CannotCastValueException {
        // Setup
        Map<String, EntityProperty> properties = new HashMap<>();
        properties.put("name", new StringProperty("Test Entity"));
        
        // Execution
        Entity entity = new Entity("TestType", properties);
        entity.get("name", BooleanProperty.class); // This should throw an exception
    }
    
    @Test
    public void testEntityConstructorWithProperties() throws CannotCastValueException {
        Map<String, EntityProperty> properties = new HashMap<>();
        properties.put("key1", new StringProperty("value1"));
        properties.put("key2", new BooleanProperty(true));
        
        Entity entity = null;
        try {
            entity = new Entity("testType", properties);
        } catch (CannotCastValueException e) {
            fail("CannotCastValueException should not be thrown");
        }
        
        assertNotNull(entity);
        assertEquals("testType", entity.getType().getValue());
        assertEquals("value1", entity.get("key1", StringProperty.class).getValue());
        assertEquals(true, entity.get("key2", BooleanProperty.class).getValue());
    }

}