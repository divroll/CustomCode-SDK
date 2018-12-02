/**
 * Copyright 2017 Dotweblabs Web Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.divroll.functions.rest;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author <a href="mailto:kerby@dotweblabs.com">Kerby Martino</a>
 * @version 1.0
 * @since 1.0
 */
public class CustomCodeResponse {
	  private final int responseStatus;
	  private final Map<String, ?> responseMap;
	  public CustomCodeResponse(int responseCode, Map<String, ?> responseMap) {
		    this.responseStatus = responseCode;
		    this.responseMap = responseMap;
	  }	  
	  public CustomCodeResponse(int responseCode) {
		    this.responseStatus = responseCode;
		    this.responseMap = new HashMap<String, Object>();
	  }
	public int getResponseStatus() {
		return responseStatus;
	}
	public Map<String, ?> getResponseMap() {
		return responseMap;
	}	  
}