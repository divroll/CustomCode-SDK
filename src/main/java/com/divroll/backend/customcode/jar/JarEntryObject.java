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
package com.divroll.backend.customcode.jar;

import com.divroll.backend.customcode.method.CustomCodeMethod;

import java.util.List;

/**
 * The JarEntryObject is an abstract class that represents an entry in a JAR file.
 * Each JarEntryObject is expected to provide a list of CustomCodeMethod objects.
 * These methods represent the custom code methods associated with the entry.
 *
 * Subclasses of JarEntryObject should implement the methods() method to provide
 * the specific list of CustomCodeMethod objects for that entry.
 *
 * @author <a href="mailto:kerby@divroll.com">Kerby Martino</a>
 * @version 0-SNAPSHOT
 * @since 0-SNAPSHOT
 */
public abstract class JarEntryObject {
	public abstract List<CustomCodeMethod> methods();
}
