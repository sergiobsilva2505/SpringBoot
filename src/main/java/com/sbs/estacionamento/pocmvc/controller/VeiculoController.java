package com.sbs.estacionamento.pocmvc.controller;

import com.sbs.estacionamento.pocmvc.dto.NovoVeiculoDto;
import com.sbs.estacionamento.pocmvc.entities.Veiculo;
import com.sbs.estacionamento.pocmvc.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Veiculo> findVehicle(@PathVariable Integer id){
        Veiculo veiculo = veiculoService.findById(id);
        return ResponseEntity.ok().body(veiculo);
    }

    @PostMapping
    public ResponseEntity<Void> insertVehicle(@RequestBody NovoVeiculoDto objDto){
        Veiculo obj = veiculoService.dtoFromVeiculo(objDto);
        obj = veiculoService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
