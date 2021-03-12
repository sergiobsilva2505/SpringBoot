package com.sbs.estacionamento.pocmvc.controller;

import com.sbs.estacionamento.pocmvc.dto.NovoVeiculoDto;
import com.sbs.estacionamento.pocmvc.entities.Veiculo;
import com.sbs.estacionamento.pocmvc.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    public VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<Veiculo>> allVehicles(){
        List<Veiculo> veiculos =  veiculoService.findAll();
        return ResponseEntity.ok().body(veiculos);
    }

    @GetMapping("/{id}")
    public Veiculo findVehicle(@PathVariable Integer id){
        Veiculo veiculo = veiculoService.findById(id);
        return veiculo;
    }

    @PostMapping
    public void insertVehicle(@RequestBody NovoVeiculoDto objDto){
        veiculoService.insert(objDto);
    }

}
