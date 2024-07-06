package com.divroll.core;

import com.divroll.sdkapi.EntityId;

public class EntityNotExistsException extends EntityStoreException {

    public EntityNotExistsException(EntityId entityId) {
        super(String.format("Entity with id %s does not exist", entityId.toString()));
    }
}
