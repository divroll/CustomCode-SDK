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

import com.divroll.core.CannotCastValueException;

public abstract class EntityProperty<T> {
    protected final T property;

    public EntityProperty(T property) {
        if (property == null) {
            throw new IllegalArgumentException("Property cannot be null");
        }
        this.property = property;
    }

    public T getValue() {
        return property;
    }

    @Override
    public boolean equals(Object e) {
        if (this == e) return true;
        if (e == null || getClass() != e.getClass()) return false;

        EntityProperty entityProperty = (EntityProperty) e;

        return property == null ? entityProperty.property == null : property.equals(entityProperty.property);
    }

    @Override
    public int hashCode() {
        return property != null ? property.hashCode() : 0;
    }

    @Override
    public String toString() {
        return (property == null ? "null" : property.toString());
    }

    public <U extends EntityProperty> boolean isA(Class<U> cls) {
        return cls.isAssignableFrom(this.getClass());
    }

    public <U extends EntityProperty> U asA(Class<U> cls) throws CannotCastValueException {
        if(isA(cls)) {
            return cls.cast(this);
        }
        else {
            throw new CannotCastValueException(cls.getCanonicalName(), this.getClass().getCanonicalName());
        }
    }
}
