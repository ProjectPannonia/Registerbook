package com.registerbook.registerbook.controller.errorHandler.customException;


public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){
        super(message);
    }
}
