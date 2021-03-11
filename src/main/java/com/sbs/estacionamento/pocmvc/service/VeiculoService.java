package com.sbs.estacionamento.pocmvc.service;

import com.sbs.estacionamento.pocmvc.model.Veiculo;
import com.sbs.estacionamento.pocmvc.model.repo.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    public VeiculoRepository veiculoRepository;

    public List<Veiculo> findAll(){
        List<Veiculo> list = veiculoRepository.findAll();
        return list;
    }
}
