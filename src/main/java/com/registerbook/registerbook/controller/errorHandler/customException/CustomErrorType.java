package com.registerbook.registerbook.controller.errorHandler.customException;

import com.registerbook.registerbook.model.Member;

public class CustomErrorType extends Member {
    private String errorMessage;

    public CustomErrorType(final String errorMessage){
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage(){
        return errorMessage;
    }
}
