package com.sbs.estacionamento.pocmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MovimentacaoController {

    @GetMapping("/")
    public String hello(Model model){
        model.addAttribute("name", " FCamara!!!");
        return "hello";
    }
}
