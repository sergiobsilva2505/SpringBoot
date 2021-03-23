package com.sbs.estacionamento.pocmvc.controller.exceptions;

import com.sbs.estacionamento.pocmvc.service.exceptions.VeiculoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(VeiculoNotFoundException.class)
    public ResponseEntity<StandardError> veiculoNotFound(VeiculoNotFoundException e){
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),
                e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
