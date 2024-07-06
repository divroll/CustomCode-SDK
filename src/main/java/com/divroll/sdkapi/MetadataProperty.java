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
        EntityProperty property = getValue().get(name);
        if (property != null && property.isA(clazz)) {
            return (T) property.asA(clazz);
        } else {
            throw new CannotCastValueException(clazz.getCanonicalName(), property != null ? property.getClass().getCanonicalName() : "null");
        }
    }

    public void set(String name, EntityProperty value) {
        getValue().put(name, value);
    }
}