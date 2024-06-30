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

package com.divroll.sdkapi.http.request;

import com.divroll.sdkapi.http.Header;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public abstract class HttpRequest {
    protected static Set<Header> EmptyHeaders = new HashSet<Header>();

    private URL url;
    private Set<Header> headers;

    public HttpRequest(String url, Set<Header> headers) throws MalformedURLException {
        this.url = new URL(url);
        this.headers = headers;
    }

    public URL getUrl() {
        return url;
    }

    public Set<Header> getHeaders() {
        return headers;
    }
}
