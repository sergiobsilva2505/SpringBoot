package com.sbs.estacionamento.pocmvc.exceptions;

import com.sbs.estacionamento.pocmvc.entities.Veiculo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VeiculoNotFoundException extends RuntimeException {
    private static final  long serialVersionUID = 1L;

    public VeiculoNotFoundException() {
    }

    public VeiculoNotFoundException(String message) {
        super(message);
    }

    public VeiculoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VeiculoNotFoundException(Throwable cause) {
        super(cause);
    }

}
