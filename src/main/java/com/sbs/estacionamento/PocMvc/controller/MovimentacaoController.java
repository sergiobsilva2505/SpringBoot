package com.sbs.estacionamento.pocmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MovimentacaoController {

    @GetMapping("/")
    public String hello(HttpServletRequest request){
        request.setAttribute("name", "Vania");
        return "hello";
    }
}
