/*
 * Copyright (C) 2024 Divroll
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.divroll.sdkapi;

import java.util.*;

import com.divroll.core.CannotCastValueException;

public class Entity extends EntityProperty<Map<String, EntityProperty>> {

    private static final String METADATA_KEY = "_metadata";
    private static final String ID_KEY = "id";
    private static final String ACL_READ_KEY = "aclRead";
    private static final String ACL_WRITE_KEY = "aclWrite";
    private static final String PUBLIC_READ_KEY = "publicRead";
    private static final String PUBLIC_WRITE_KEY = "publicWrite";

    public Entity(String type, Map<String, EntityProperty> properties) throws CannotCastValueException {
        super(properties);
        setType(type);
    }

    public Entity(String type, String property, EntityProperty value) throws CannotCastValueException {
        super(new LinkedHashMap<String, EntityProperty>() {{
            put(property, value);
        }});
        setType(type);
    }

    public EntityProperty get(String name) {
        return getValue().get(name);
    }

    public <T extends EntityProperty> T get(String name, Class<T> clazz) throws CannotCastValueException {
        return asA(clazz);
    }

    public void set(String name, EntityProperty value) {
        getValue().put(name, value);
    }
    
    public EntityId getEntityId() throws CannotCastValueException {
        return getMetadataProperty(ID_KEY, EntityId.class);
    }

    public BooleanProperty getPublicRead() throws CannotCastValueException {
        return getMetadataProperty(PUBLIC_READ_KEY, BooleanProperty.class);
    }

    public void setPublicRead(BooleanProperty isPublicRead) throws CannotCastValueException {
        setMetadataProperty(PUBLIC_READ_KEY, isPublicRead);
    }

    public BooleanProperty getPublicWrite() throws CannotCastValueException {
        return getMetadataProperty(PUBLIC_WRITE_KEY, BooleanProperty.class);
    }

    public void setPublicWrite(BooleanProperty isPublicWrite) throws CannotCastValueException {
        setMetadataProperty(PUBLIC_WRITE_KEY, isPublicWrite);
    }

    public void setReadAccessIds(List<EntityId> readAccessIds) throws CannotCastValueException {
        setMetadataProperty(ACL_READ_KEY, new CollectionProperty<>(readAccessIds));
    }

    public List<EntityId> getReadAccessIds() throws CannotCastValueException {
        return getMetadataCollection(ACL_READ_KEY);
    }

    public void setWriteAccessIds(List<EntityId> writeAccessIds) throws CannotCastValueException {
        setMetadataProperty(ACL_WRITE_KEY, new CollectionProperty<>(writeAccessIds));
    }

    public List<EntityId> getWriteAccessIds() throws CannotCastValueException {
        return getMetadataCollection(ACL_WRITE_KEY);
    }
    
    public void addReadAccessId(EntityId readAccessId) throws CannotCastValueException {
        List<EntityId> readAccessIds = getReadAccessIds();
        if (!readAccessIds.contains(readAccessId)) {
            readAccessIds.add(readAccessId);
            setReadAccessIds(readAccessIds);
        }
    }
    
    public void addWriteAccessId(EntityId writeAccessId) throws CannotCastValueException {
        List<EntityId> writeAccessIds = getWriteAccessIds();
        if (!writeAccessIds.contains(writeAccessId)) {
            writeAccessIds.add(writeAccessId);
            setWriteAccessIds(writeAccessIds);
        }
    }
    
    public void addWriteAccess(Entity writeEntity) throws CannotCastValueException {
        addWriteAccessId(writeEntity.getEntityId());
    }
    
    public void addReadAccess(Entity readEntity) throws CannotCastValueException {
        addReadAccessId(readEntity.getEntityId());
    }
    
    private void setType(String type) throws CannotCastValueException {
        setMetadataProperty("type", new StringProperty(type));
    }
    
    private <T extends EntityProperty> T getMetadataProperty(String key, Class<T> clazz) throws CannotCastValueException {
        MetadataProperty metadata = get(METADATA_KEY, MetadataProperty.class);
        return metadata != null ? metadata.get(key, clazz) : null;
    }

    private void setMetadataProperty(String key, EntityProperty value) throws CannotCastValueException {
        MetadataProperty metadata = get(METADATA_KEY, MetadataProperty.class);
        if (metadata == null) {
            metadata = new MetadataProperty(key, value);
            set(METADATA_KEY, metadata);
        } else {
            metadata.set(key, value);
        }
    }

    private List<EntityId> getMetadataCollection(String key) throws CannotCastValueException {
        MetadataProperty metadata = get(METADATA_KEY, MetadataProperty.class);
        if (metadata != null) {
            CollectionProperty<EntityId> collectionProperty = metadata.get(key, CollectionProperty.class);
            if (collectionProperty != null) {
                return new ArrayList<>(collectionProperty.getValue());
            }
        }
        return new ArrayList<>();
    }
}
