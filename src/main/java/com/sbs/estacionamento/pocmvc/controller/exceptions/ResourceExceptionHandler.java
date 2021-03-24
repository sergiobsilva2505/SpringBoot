package com.sbs.estacionamento.pocmvc.controller.exceptions;

import com.sbs.estacionamento.pocmvc.service.exceptions.VeiculoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ResourceExceptionHandler  {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(VeiculoNotFoundException.class)
    public ResponseEntity<StandardError> veiculoNotFound(VeiculoNotFoundException e){
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),
                e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<List<ErroVeiculoFormDto>> handleValidation(MethodArgumentNotValidException exception){
        List<ErroVeiculoFormDto> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroVeiculoFormDto erro = new ErroVeiculoFormDto(e.getField(), mensagem);
            dto.add(erro);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }


}
