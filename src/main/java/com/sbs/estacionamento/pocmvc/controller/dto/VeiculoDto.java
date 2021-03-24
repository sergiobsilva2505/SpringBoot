package com.sbs.estacionamento.pocmvc.controller.dto;

import com.sbs.estacionamento.pocmvc.entities.Veiculo;
import com.sbs.estacionamento.pocmvc.entities.enums.TipoVeiculo;

import java.util.List;
import java.util.stream.Collectors;

public class VeiculoDto {

    private Integer id;
    private String marca;
    private String modelo;
    private String cor;
    private String placa;
    private TipoVeiculo tipo;

    public VeiculoDto(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.cor = veiculo.getCor();
        this.placa = veiculo.getPlaca();
        this.tipo = veiculo.getTipo();

    }

    public static List<VeiculoDto> convertAll(List<Veiculo> veiculos) {
        return veiculos.stream().map(VeiculoDto::new).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public String getPlaca() {
        return placa;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

}
