package com.course.practicaljava.api.server;

import com.course.practicaljava.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Component
public class ExceptionHandlerController {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);

//    @ExceptionHandler(value = IllegalArgumentException.class)
//    private ResponseEntity<ErrorResponse> handleInvalidColorException(IllegalArgumentException e) {
//        var message = "Exception" + e.getMessage();
//        LOG.warn(message);
//
//        var errorResponse = new ErrorResponse(message, LocalDateTime.now());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//    }

}
