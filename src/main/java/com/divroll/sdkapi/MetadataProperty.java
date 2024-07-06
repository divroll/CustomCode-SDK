package com.divroll.sdkapi;

import com.divroll.core.CannotCastValueException;

import java.util.LinkedHashMap;
import java.util.Map;

public class MetadataProperty extends EntityProperty<Map<String, EntityProperty>> {

    public MetadataProperty(Map<String, EntityProperty> property) {
        super(property);
    }
    
    public MetadataProperty(String property, EntityProperty value) {
        super(new LinkedHashMap<String, EntityProperty>() {{
            put(property, value);
        }});
    }
    
    public EntityProperty get(String name) {
        return getValue().get(name);
    }

    public <T extends EntityProperty> T get(String name, Class<T> clazz) 
        throws CannotCastValueException {
        return asA(clazz);
    }

    public void set(String name, EntityProperty value) {
        getValue().put(name, value);
    }
}