package com.sbs.estacionamento.pocmvc.service;

import com.sbs.estacionamento.pocmvc.PocMvcApplicationTests;
import com.sbs.estacionamento.pocmvc.entities.Veiculo;
import com.sbs.estacionamento.pocmvc.exceptions.VeiculoDataIntegrityViolationException;
import com.sbs.estacionamento.pocmvc.exceptions.VeiculoNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VeiculoServiceTest extends PocMvcApplicationTests {

    @Autowired
    private VeiculoService veiculoService;

    @Test
    public  void deveRetornarUmaListaDeVeiculosTest(){
        List<Veiculo> veiculos = veiculoService.findAll();
        int countVeiculo = veiculoService.findAll().size();
        Assert.assertEquals(countVeiculo, veiculos.size());
    }

    @Test
    public  void deveRetornarUmVeiculoPeloId(){
        Integer id = 20;
        Veiculo veiculo = veiculoService.findById(id);
        Assert.assertNotNull(veiculo);
        Assert.assertEquals(id, veiculo.getId());
    }

    @Test
    public void deveRetornarUmaExceptionParaBuscaPorIdDeVeiculoQueNaoExiste(){
        Assert.assertThrows(VeiculoNotFoundException.class, () -> veiculoService.findById(5));
    }

    @Test
    public void deveLancarExcecaoAoTentarDeletarVeiculoPorIdEOVeiculoJaNaoExistirTest(){
        Integer id = 31;
        Assert.assertThrows(VeiculoNotFoundException.class, () -> veiculoService.delete(id));
    }

}