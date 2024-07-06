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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A simple implementation of a MultiMap.
 * A MultiMap is a map that allows mapping of a single key to multiple values.
 */
public class MultiMap<K, V> {
    private final Map<K, Set<V>> map = new HashMap<>();

    /**
     * Adds a value to the collection associated with a specific key in the MultiMap.
     * @param key The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     */
    public void put(K key, V value) {
        map.computeIfAbsent(key, k -> new HashSet<>()).add(value);
    }

    /**
     * Gets the collection of values associated with a specific key in the MultiMap.
     * @param key The key whose associated values are to be returned.
     * @return The collection of values associated with the specified key, or null if no mapping for the key exists.
     */
    public Collection<V> get(K key) {
        return map.get(key);
    }

    /**
     * Removes a specific value associated with a specific key in the MultiMap.
     * @param key The key with which the specified value is associated.
     * @param value The value to be removed from the collection of values associated with the specified key.
     * @return true if the value was removed, false otherwise.
     */
    public boolean remove(K key, V value) {
        Set<V> values = map.get(key);
        if (values != null) {
            boolean removed = values.remove(value);
            if (values.isEmpty()) {
                map.remove(key);
            }
            return removed;
        }
        return false;
    }

    /**
     * Checks whether the MultiMap contains a mapping for the specified key.
     * @param key The key whose presence in the MultiMap is to be tested.
     * @return true if this map contains a mapping for the specified key.
     */
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    /**
     * Checks whether the MultiMap contains a mapping for the specified value associated with the specified key.
     * @param key The key with which the specified value is to be associated.
     * @param value The value whose presence in the collection of values associated with the specified key is to be tested.
     * @return true if this map maps one or more keys to the specified value.
     */
    public boolean containsEntry(K key, V value) {
        Set<V> values = map.get(key);
        return values != null && values.contains(value);
    }

    /**
     * Returns the set of keys contained in this MultiMap.
     * @return a set view of the keys contained in this map.
     */
    public Set<K> keySet() {
        return map.keySet();
    }

    /**
     * Clears all key-value mappings from this MultiMap.
     */
    public void clear() {
        map.clear();
    }

    /**
     * Returns the number of key-value mappings in this MultiMap.
     * @return the number of key-value mappings in this map.
     */
    public int size() {
        return map.size();
    }
}