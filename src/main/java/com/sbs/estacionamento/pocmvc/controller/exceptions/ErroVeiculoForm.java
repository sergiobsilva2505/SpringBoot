package com.sbs.estacionamento.pocmvc.controller.exceptions;

public class ErroVeiculoForm {

    private String campo;
    private String erro;

    public ErroVeiculoForm(String campo, String erro) {
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
