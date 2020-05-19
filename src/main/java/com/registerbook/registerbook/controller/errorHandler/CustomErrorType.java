package com.registerbook.registerbook.controller.errorHandler;

import com.registerbook.registerbook.repository.model.Member;

public class CustomErrorType extends Member {
    private String errorMessage;

    public CustomErrorType(final String errorMessage){
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage(){
        return errorMessage;
    }
}
