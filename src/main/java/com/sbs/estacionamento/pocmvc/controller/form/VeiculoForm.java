package com.sbs.estacionamento.pocmvc.controller.form;

import com.sbs.estacionamento.pocmvc.entities.enums.TipoVeiculo;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class VeiculoForm {


    @NotEmpty(message = "Marca não pode ser nula ou vazia!")
    @Length(min = 5)
    private String marca;

    @NotEmpty(message = "Modelo não pode ser nulo ou vazio!")
    @Length(min = 5)
    private String modelo;

    @NotEmpty(message = "Cor não pode ser nula ou vazia!")
    @Length(min = 5)
    private String cor;

    @NotEmpty(message = "Placa não pode ser nula ou vazia!")
    @Length(min = 5)
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
