package com.sbs.estacionamento.pocmvc.controller;

import com.sbs.estacionamento.pocmvc.model.Veiculo;
import com.sbs.estacionamento.pocmvc.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    public VeiculoService veiculoService;

    @GetMapping
    public List<Veiculo> allVehicles(){
        List<Veiculo> veiculos =  veiculoService.findAll();
        return veiculos;
    }

    @GetMapping("/{id}")
    public Veiculo findVehicle(@PathVariable Integer id){
        Veiculo veiculo = veiculoService.findById(id);
        return veiculo;
    }

}
