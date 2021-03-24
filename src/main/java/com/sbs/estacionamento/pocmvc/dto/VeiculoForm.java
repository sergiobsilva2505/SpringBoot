package com.sbs.estacionamento.pocmvc.dto;

import com.sbs.estacionamento.pocmvc.entities.enums.TipoVeiculo;

public class VeiculoForm {


    private String marca;
    private String modelo;
    private String cor;
    private String placa;

    private TipoVeiculo tipo;

    public VeiculoForm(){

    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }
}
