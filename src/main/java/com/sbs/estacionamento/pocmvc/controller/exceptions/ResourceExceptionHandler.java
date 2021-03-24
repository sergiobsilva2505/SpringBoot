package com.sbs.estacionamento.pocmvc.controller.exceptions;

import com.sbs.estacionamento.pocmvc.service.exceptions.VeiculoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ResourceExceptionHandler  {

    @ExceptionHandler(VeiculoNotFoundException.class)
    public ResponseEntity<StandardError> veiculoNotFound(VeiculoNotFoundException e){
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),
                e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<List<ErroVeiculoForm>> handleVelidation(MethodArgumentNotValidException exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


}
