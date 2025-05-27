package com.juggle.chat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        if(ex instanceof JimException) {
            JimException jimEx = (JimException) ex;
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("code", jimEx.getCode());
            errorMap.put("msg", jimEx.getMsg());
            return new ResponseEntity<>(errorMap, HttpStatus.OK);
        }else{
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("code",500);
            errorMap.put("msg", ex.getMessage());
            return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}