package com.registerbook.registerbook.controller.errorHandler.customException;


public class ResourceNotFoundException extends RuntimeException {
    /*public HttpStatus throwException(){
        return HttpStatus.NOT_FOUND;
    }*/
    public ResourceNotFoundException(String message){
        super(message);
        //System.out.println("Not found");
    }
}
