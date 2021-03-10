package com.sbs.estacionamento.pocmvc.controller;

import com.sbs.estacionamento.pocmvc.model.Veiculo;
import com.sbs.estacionamento.pocmvc.model.enums.TipoVeiculo;
import com.sbs.estacionamento.pocmvc.model.repo.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class VeiculoController {

    @Autowired
    public VeiculoRepository veiculoRepository;

    @GetMapping("/veiculos")
    public String todosVeiculos(Model model){

        Veiculo veiculo = new Veiculo(null, "Volkswagwn", "Gol", "prata",
                "BXF8081", TipoVeiculo.CARRO);
        Veiculo veiculo1 = new Veiculo(null, "Volkswagwn", "Kombi", "rosa",
                "BtF0001", TipoVeiculo.CARRO);
        List<Veiculo> veiculos = Arrays.asList(veiculo, veiculo1);
        model.addAttribute("veiculos", veiculos);
        return "veiculos";
    }
}
