package com.example.demo.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@RestControllerAdvice
public class ApiExceptionHandler extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = new LinkedHashMap();

        Map<String, Object> attributes = super.getErrorAttributes(webRequest, options);

        long timestamp = ((Date) attributes.get("timestamp")).getTime();



        errorAttributes.put("status", attributes.get("status"));
        errorAttributes.put("message", attributes.get("message"));
        errorAttributes.put("path", attributes.get("path"));
        errorAttributes.put("errorTime", timestamp);

        if(attributes.containsKey("errors")){
            List<FieldError> fieldErrors = (List<FieldError>) attributes.get("errors");
            Map<String, String> errors = new HashMap<>();
            for(FieldError fieldError: fieldErrors){
                errors.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            errorAttributes.put("errors",errors);
        }

        return errorAttributes;

    }
}
