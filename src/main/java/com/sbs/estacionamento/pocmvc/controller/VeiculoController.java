package com.sbs.estacionamento.pocmvc.controller;

import com.sbs.estacionamento.pocmvc.controller.form.EditaVeiculoForm;
import com.sbs.estacionamento.pocmvc.controller.dto.VeiculoDto;
import com.sbs.estacionamento.pocmvc.controller.form.VeiculoForm;
import com.sbs.estacionamento.pocmvc.entities.Veiculo;
import com.sbs.estacionamento.pocmvc.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    @GetMapping
    public ResponseEntity<List<VeiculoDto>> allVehicles(){
        List<Veiculo> veiculos =  veiculoService.findAll();
        return ResponseEntity.ok().body(VeiculoDto.convertAll(veiculos));
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<VeiculoDto> findVehicle(@PathVariable Integer id){
        Veiculo veiculo = veiculoService.findById(id);
        return ResponseEntity.ok().body(new VeiculoDto(veiculo));
    }

    /**
     *
     * @param objForm
     * @return
     */
    @PostMapping
    public ResponseEntity<VeiculoDto> insertVehicle(@Valid @RequestBody VeiculoForm objForm){
        Veiculo obj = veiculoService.dtoFromVeiculo(objForm);
        System.out.println(obj.getPlaca());
        obj = veiculoService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(new VeiculoDto(obj));
    }

    /**
     *
     * @param objForm
     * @param id
     * @return
     */
    // to do - mudar para requisição patch e verificar quais atribuots poderão ser alterados
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody EditaVeiculoForm objForm, @PathVariable Integer id){
        Veiculo obj = veiculoService.editaVeiculo(objForm, id);
        veiculoService.update(obj);
        return ResponseEntity.ok().build();
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
