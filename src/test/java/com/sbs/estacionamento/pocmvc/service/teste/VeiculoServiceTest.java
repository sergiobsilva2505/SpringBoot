package com.sbs.estacionamento.pocmvc.service.teste;

import com.sbs.estacionamento.pocmvc.entities.Veiculo;
import com.sbs.estacionamento.pocmvc.repo.VeiculoRepository;
import com.sbs.estacionamento.pocmvc.service.VeiculoService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class VeiculoServiceTest {

    @Autowired
    private VeiculoService veiculoService;

    @Test
    public void deveRetornarUmVeiculoPorId() {
        Integer id = 13;
        Veiculo optional = veiculoService.findById(id);
        Assert.assertEquals(id, optional.getId());
        Assert.assertNotNull(optional);
    }

}