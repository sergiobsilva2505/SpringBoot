package com.sbs.estacionamento.pocmvc.controller.form;

import com.sbs.estacionamento.pocmvc.entities.enums.TipoVeiculo;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class VeiculoForm {


    @NotEmpty(message = "marca não pode ser nula ou vazia!")
    @Length(min = 5, max = 25)
    private String marca;

    @NotEmpty(message = "modelo não pode ser nulo ou vazio!")
    @Length(min = 5, max = 25)
    private String modelo;

    @NotEmpty(message = "cor não pode ser nula ou vazia!")
    @Length(min = 5, max = 25)
    private String cor;

    /* Regex precisa ser estudado mais a fundo*/
    @Pattern(regexp = "[A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{2}|[A-Z]{3}[0-9]{4}",
            message = "placa deve ter o padrao brasileiro ou mercosul!")
    @NotNull(message = "placa não pode ser nula ou vazia!")
    private String placa;

    @NotNull(message = "tipo não pode ser nulo!")
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
