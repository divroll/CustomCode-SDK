package com.divroll.core;

public class EntityStoreException extends Exception {

    private static final long serialVersionUID = 1L;

    public EntityStoreException(String message) {
        super(message);
    }

    public EntityStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityStoreException(Throwable cause) {
        super(cause);
    }
}
