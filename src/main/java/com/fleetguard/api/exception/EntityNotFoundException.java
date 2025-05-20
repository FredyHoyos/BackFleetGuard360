package com.fleetguard.api.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Integer id) {super("Could not find entity with id " + id);}
    public EntityNotFoundException(String message) { super(message);}
}
