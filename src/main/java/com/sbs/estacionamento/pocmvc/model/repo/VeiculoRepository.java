package com.sbs.estacionamento.pocmvc.model.repo;

import com.sbs.estacionamento.pocmvc.model.Veiculo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends CrudRepository<Veiculo, Integer> {
    
    List<Veiculo> findAll();
}
