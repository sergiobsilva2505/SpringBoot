package com.sbs.estacionamento.pocmvc.controller.exceptions;

public class ErroVeiculoFormDto {

    private String campo;
    private String erro;

    public ErroVeiculoFormDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
