package com.divroll.core;

import com.divroll.sdkapi.EntityId;

public class InvalidIdException extends EntityStoreException {

    public InvalidIdException(EntityId id) {
        super(String.format("Invalid id %s", id));
    }
}
