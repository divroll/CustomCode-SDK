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

package com.divroll.sdkapi.http;

import com.divroll.sdkapi.http.exceptions.AccessDeniedException;
import com.divroll.sdkapi.http.exceptions.TimeoutException;
import com.divroll.sdkapi.http.request.DeleteRequest;
import com.divroll.sdkapi.http.request.GetRequest;
import com.divroll.sdkapi.http.request.PostRequest;
import com.divroll.sdkapi.http.request.PutRequest;
import com.divroll.sdkapi.http.response.HttpResponse;

import java.util.concurrent.Future;

public abstract class HttpService {
    /**
     * determines whether the given URL is on the whitelist
     * @param url the URL to test
     * @return true if it is on the whitelist, false otherwise
     */
    public abstract boolean isWhitelisted(String url);

    /**
     * execute a GET request
     * @param req the request to execute
     * @return the response that resulted from the GET request
     * @throws AccessDeniedException if the request was rate limited, whitelisted,
     * or for any other reason denied by the Divroll custom code environment
     */
    public abstract HttpResponse get(GetRequest req) throws AccessDeniedException, TimeoutException;

    /**
     * execute a GET request in the background
     * @param req the request to execute
     * @return a future representing the response that will result from the GET request
     * @throws AccessDeniedException if the request was rate limited, whitelisted,
     * or for any other reason denied by the Divroll custom code environment
     */
    public abstract Future<HttpResponse> getAsync(GetRequest req) throws AccessDeniedException;

    /**
     * execute a POST request
     * @param req the request to execute
     * @return the response that resulted from the GET request
     * @throws AccessDeniedException if the request was rate limited, whitelisted,
     * or for any other reason denied by the Divroll custom code environment
     */
    public abstract HttpResponse post(PostRequest req) throws AccessDeniedException, TimeoutException;

    /**
     * execute a POST request in the background
     * @param req the request to execute
     * @return a future representing the response that will result from the GET request
     * @throws AccessDeniedException if the request was rate limited, whitelisted,
     * or for any other reason denied by the Divroll custom code environment
     */
    public abstract Future<HttpResponse> postAsync(PostRequest req) throws AccessDeniedException;

    /**
     * execute a PUT request
     * @param req the request to execute
     * @return the response that resulted from the GET request
     * @throws AccessDeniedException if the request was rate limited, whitelisted,
     * or for any other reason denied by the Divroll custom code environment
     */
    public abstract HttpResponse put(PutRequest req) throws AccessDeniedException, TimeoutException;

    /**
     * execute a PUT request in the background
     * @param req the request to execute
     * @return a future representing the response that will result from the GET request
     * @throws AccessDeniedException if the request was rate limited, whitelisted,
     * or for any other reason denied by the Divroll custom code environment
     */
    public abstract Future<HttpResponse> putAsync(PutRequest req) throws AccessDeniedException;

    /**
     * execute a DELETE request
     * @param req the request to execute
     * @return the response that resulted from the GET request
     * @throws AccessDeniedException if the request was rate limited, whitelisted,
     * or for any other reason denied by the Divroll custom code environment
     */
    public abstract HttpResponse delete(DeleteRequest req) throws AccessDeniedException, TimeoutException;

    /**
     * execute a DELETE request in the background
     * @param req the request to execute
     * @return a future representing the response that will result from the GET request
     * @throws AccessDeniedException if the request was rate limited, whitelisted,
     * or for any other reason denied by the Divroll custom code environment
     */
    public abstract Future<HttpResponse> deleteAsync(DeleteRequest req) throws AccessDeniedException;
}
