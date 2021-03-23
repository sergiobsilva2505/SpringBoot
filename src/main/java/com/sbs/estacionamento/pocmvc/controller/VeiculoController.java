package com.sbs.estacionamento.pocmvc.controller;

import com.sbs.estacionamento.pocmvc.dto.EditaVeiculoDto;
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

    /**
     *
     * @return
     */
    // Response Entity?
    @GetMapping
    public ResponseEntity<List<Veiculo>> allVehicles(){
        List<Veiculo> veiculos =  veiculoService.findAll();
        return ResponseEntity.ok().body(veiculos);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Veiculo> findVehicle(@PathVariable Integer id){
        Veiculo veiculo = veiculoService.findById(id);
        return ResponseEntity.ok().body(veiculo);
    }

    /**
     *
     * @param objDto
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> insertVehicle( @RequestBody NovoVeiculoDto objDto){
        Veiculo obj = veiculoService.dtoFromVeiculo(objDto);
        obj = veiculoService.insert(obj);
        // retorna uri do novo recurso criado. ???
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     *
     * @param objDto
     * @param id
     * @return
     */
    // to do - mudar para requisição patch e verificar quais atribuots poderão ser alterados
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody EditaVeiculoDto objDto, @PathVariable Integer id){
        Veiculo obj = veiculoService.editaVeiculo(objDto, id);
        veiculoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
