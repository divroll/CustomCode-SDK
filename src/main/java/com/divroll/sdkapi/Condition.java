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

/**
 * Represents an abstract condition with a specific property and a binary operation.
 * Conditions can be combined using various operations such as intersection, union, 
 * subtraction, and concatenation.
 */
public abstract class Condition {

    /**
     * The property associated with this condition.
     */
    protected final String property;
    
    /**
     * The binary operation to be applied to this condition.
     */
    protected BinaryOperation operation;

    /**
     * Constructs a Condition for the specified property
     * @param property the property associated with this condition
     */
    public Condition(String property) {
        this.property = property;
    }

    /**
     * Returns the property associated with this condition.
     * @return the property associated with this condition.
     */
    public String getProperty() {
        return property;
    }

    /**
     * Combines this condition with another condition using the intersection operation.
     * Intersection finds common elements between two conditions.
     * @param condition the condition to intersect with.
     * @return this condition after applying the intersection operation.
     */
    public Condition intersect(Condition condition) {
        this.operation = new Intersect(condition);
        return this;
    }

    /**
     * Combines this condition with another condition using the union operation.
     * Union merges elements from both conditions, including duplicates. 
     * @param condition the condition to union with.
     * @return this condition after applying the union operation.
     */
    public Condition union(Condition condition) {
        this.operation = new Union(condition);
        return this;
    }

    /**
     * Combines this condition with another condition using the minus operation.
     * Minus subtracts elements of the specified condition from this condition. 
     * @param condition the condition to subtract from this condition.
     * @return this condition after applying the minus operation.
     */
    public Condition minus(Condition condition) {
        this.operation = new Minus(condition);
        return this;
    }

    /**
     * Combines this condition with another condition using the concatenation operation.
     * Concatenation appends elements of the specified condition to this condition.
     * @param condition the condition to concatenate with.
     * @return this condition after applying the concatenation operation.
     */
    public Condition concat(Condition condition) {
        this.operation = new Concat(condition);
        return this;
    }
}
