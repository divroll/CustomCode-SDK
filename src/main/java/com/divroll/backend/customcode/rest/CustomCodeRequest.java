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
package com.divroll.backend.customcode.rest;

import com.divroll.backend.customcode.MethodVerb;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

/**
 * The CustomCodeRequest class represents a request made to a custom code method.
 * It encapsulates details about the HTTP verb used (GET, POST, PUT, DELETE), the request URL,
 * any parameters passed with the request, the body of the request as an InputStream,
 * the name of the method being called, and a counter for tracking the number of requests.
 *
 * This class provides getter methods for all these properties. It also provides a method
 * to get the body of the request as a String.
 *
 * @author <a href="mailto:kerby@divroll.com">Kerby Martino</a>
 * @version 0-SNAPSHOT
 * @since 0-SNAPSHOT
 */
public class CustomCodeRequest {
	private final MethodVerb verb;
	private final String url;
	private final InputStream body;
	private final Map<String, String> params;
	private final String methodName;
	private final long counter;

	public CustomCodeRequest(MethodVerb verb,
							 String url,
							 Map<String, String> params,
							 InputStream body,
							 String methodName,
							 long counter) {
		this.verb = verb;
		this.url = url;
		this.methodName = methodName;
		this.params = params;
		this.counter = counter;
		this.body = body;
	}

	public MethodVerb getVerb() {
		return verb;
	}

	public String getUrl() {
		return url;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public String getMethodName() {
		return methodName;
	}

	public long getCounter() {
		return counter;
	}

	public InputStream getBody() {
		return body;
	}

	public String getStringBody() {
		if(body != null) {
			try (Scanner scanner = new Scanner(body, "UTF-8")) {
				return scanner.useDelimiter("\\A").next();
			}
		}
		return null;
	}
}
