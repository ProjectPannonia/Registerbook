package com.registerbook.registerbook.controller.errorHandler.customException;

import com.registerbook.registerbook.controller.errorHandler.customException.FieldValidationError;
import com.registerbook.registerbook.controller.errorHandler.customException.FieldValidationErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//@ControllerAdvice
public class RestValidationHandler {

    private MessageSource messageSource;

    @Autowired
    public RestValidationHandler(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    // Method to  handle validation error
    //@ExceptionHandler(MethodArgumentNotValidException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<FieldValidationErrorDetails> handleValidationError(
            MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest request) {

        FieldValidationErrorDetails fved = new FieldValidationErrorDetails();

        fved.setError_timeStamp(new Date().getTime());
        fved.setError_status(HttpStatus.BAD_REQUEST.value());
        fved.setError_title("Field validation error");
        fved.setError_detail("Input field validation failed");
        fved.setError_developer_message(methodArgumentNotValidException.getClass().getName());
        fved.setError_path(request.getRequestURI());

        BindingResult result = methodArgumentNotValidException.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        for (FieldError error : fieldErrors){
            FieldValidationError fError = processFieldError(error);
            List<FieldValidationError> fieldValidationErrorList =
                    fved.getErrors().get(error.getField());
            if(fieldValidationErrorList == null){
                fieldValidationErrorList = new ArrayList<FieldValidationError>();
            }
            fieldValidationErrorList.add(fError);
            fved.getErrors().put(error.getField(),fieldValidationErrorList);
        }
        return new ResponseEntity<FieldValidationErrorDetails>(fved,HttpStatus.BAD_REQUEST);
    }
        //method to process field error
        private FieldValidationError processFieldError ( final FieldError error){
            FieldValidationError fieldValidationError = new FieldValidationError();

            if (error != null) {
                Locale currentLocale = LocaleContextHolder.getLocale();
                String msg = messageSource.getMessage(error.getDefaultMessage(), null, currentLocale);
                fieldValidationError.setField(error.getField());
                fieldValidationError.setType(TrayIcon.MessageType.ERROR);
                fieldValidationError.setMessage(error.getDefaultMessage());
            }
            return fieldValidationError;
        }
    }
