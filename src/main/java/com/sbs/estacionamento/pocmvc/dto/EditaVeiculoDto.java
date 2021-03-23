package com.sbs.estacionamento.pocmvc.dto;

public class EditaVeiculoDto {

    private String cor;
    private String placa;

    public EditaVeiculoDto() {

    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
