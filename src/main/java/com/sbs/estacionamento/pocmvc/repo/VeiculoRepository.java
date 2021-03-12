package com.sbs.estacionamento.pocmvc.repo;

import com.sbs.estacionamento.pocmvc.entities.Veiculo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends CrudRepository<Veiculo, Integer> {
    
    List<Veiculo> findAll();
}
