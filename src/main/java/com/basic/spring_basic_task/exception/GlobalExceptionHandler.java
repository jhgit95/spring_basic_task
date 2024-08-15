package com.basic.spring_basic_task.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ScheduleException.class)
    public ResponseEntity<String> handleMissingFieldException(ScheduleException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // 다른 예외 처리 메서드들...
}
