package com.divroll.core;

import com.divroll.sdkapi.Relationship;

public class InvalidRelationException extends EntityStoreException {

    public InvalidRelationException(Relationship relationship) {
        super(String.format("Relationship name %s is not valid", relationship.getName()));
    }
}
