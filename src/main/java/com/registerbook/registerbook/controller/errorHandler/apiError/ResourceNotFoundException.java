package com.registerbook.registerbook.controller.errorHandler.apiError;


public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){
        super(message);
    }
}
