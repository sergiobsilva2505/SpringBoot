package com.sbs.estacionamento.pocmvc.repo;

import com.sbs.estacionamento.pocmvc.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

    Veiculo findByPlaca(String placa);
}
