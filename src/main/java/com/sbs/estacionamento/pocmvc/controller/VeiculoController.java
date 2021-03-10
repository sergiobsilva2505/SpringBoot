package com.sbs.estacionamento.pocmvc.controller;

import com.sbs.estacionamento.pocmvc.model.Veiculo;
import com.sbs.estacionamento.pocmvc.model.enums.TipoVeiculo;
import com.sbs.estacionamento.pocmvc.model.repo.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

@Controller
public class VeiculoController {

    @Autowired
    public VeiculoRepository veiculoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/veiculos")
    public String todosVeiculos(Model model){
        Query query = entityManager.createQuery("SELECT v from Veiculo v", Veiculo.class);
        List<Veiculo> veiculos =  query.getResultList();
        model.addAttribute("veiculos", veiculos);
        return "veiculos";
    }
}
