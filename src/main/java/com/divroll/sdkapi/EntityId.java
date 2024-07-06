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

import java.util.LinkedHashMap;
import java.util.Map;

public class EntityId extends EntityProperty<Map<String, StringProperty>> {

    public EntityId(String type) {
        super(new LinkedHashMap<String, StringProperty>() {{
            put("type", new StringProperty(type));
        }});
    }
    
    public EntityId(String type, String id) {
        super(new LinkedHashMap<String, StringProperty>() {{
            put("type", new StringProperty(type));
            put("id", new StringProperty(id));
        }});
    }

    public StringProperty getType() {
        return getValue().get("type");
    }

    public StringProperty getId() {
        return getValue().get("id");
    }
}
