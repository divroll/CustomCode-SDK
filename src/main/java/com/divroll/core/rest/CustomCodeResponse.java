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
package com.divroll.core.rest;

import java.util.Map;

/**
 * The CustomCodeResponse class represents the response from a custom code method.
 * It encapsulates details about the HTTP response status, the response body as a byte array,
 * and the response as a map.
 *
 * This class provides getter methods for all these properties.
 *
 * @author <a href="mailto:kerby@divroll.com">Kerby Martino</a>
 * @version 0-SNAPSHOT
 * @since 0-SNAPSHOT
 */
public class CustomCodeResponse {
	private final int responseStatus;
	private Map<String, ?> responseMap = null;
	private byte[] responseBody = null;
	public CustomCodeResponse(int responseCode, byte[] responseBody) {
		this.responseStatus = responseCode;
		this.responseBody = responseBody;
	}
	public CustomCodeResponse(int responseCode, Map<String, ?> responseMap) {
		this.responseStatus = responseCode;
		this.responseMap = responseMap;
	}
	public int getResponseStatus() {
		return responseStatus;
	}
	public Map<String, ?> getResponseMap() {
		return responseMap;
	}
	public byte[] getResponseBody() {
		return responseBody;
	}
}
