package com.sbs.estacionamento.PocMvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VeiculoController {

    @GetMapping("/veiculos")
    public String todosVeiculos(){
        return "veiculos";
    }
}
