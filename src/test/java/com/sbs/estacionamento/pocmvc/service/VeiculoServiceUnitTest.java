package com.sbs.estacionamento.pocmvc.service;

import com.sbs.estacionamento.pocmvc.PocMvcApplicationTests;
import com.sbs.estacionamento.pocmvc.entities.Veiculo;
import com.sbs.estacionamento.pocmvc.entities.enums.TipoVeiculo;
import com.sbs.estacionamento.pocmvc.repo.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@RunWith(MockitoJUnitRunner.class)
class VeiculoServiceUnitTest extends PocMvcApplicationTests {

    @Autowired
    private VeiculoService veiculoService;

    @Mock
    private VeiculoRepository veiculoRepoMock;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
    }





    private List<Veiculo> veiculos(){
        List<Veiculo> lista = new ArrayList<>();
        Veiculo vei01 = new Veiculo(1, "VOLKSWAGEN", "POLO", "PRATA", "ENS2923", TipoVeiculo.CARRO);
        Veiculo vei02 = new Veiculo(2, "FIAT", "MOBI", "BRANCO", "BKC7456", TipoVeiculo.CARRO);
        Veiculo vei03 = new Veiculo(3, "CITROEN", "AIRCROSS", "PRATA", "DNX4368", TipoVeiculo.CARRO);
        Veiculo vei04 = new Veiculo(4, "FERRARI", "FF F1", "VERDE", "CFK7094", TipoVeiculo.CARRO);
        Veiculo vei05 = new Veiculo(5, "CHEVROLET", "MERIVA", "AMARELO", "CYC1484", TipoVeiculo.CARRO);
        Veiculo vei06 = new Veiculo(6, "HONDA", "CG125", "VERMELHO", "BVW8642", TipoVeiculo.MOTO);
        Veiculo vei07 = new Veiculo(7, "KAWASAKI", "NINJA", "VERDE", "BFL9295", TipoVeiculo.MOTO);
        Veiculo vei08 = new Veiculo(8, "SUZUKI", "DK150FI", "AZUL", "BPA8966", TipoVeiculo.MOTO);
        Veiculo vei09 = new Veiculo(9, "YAMAHA", "FACTOR 125I UBS", "PRETA", "EAO1581", TipoVeiculo.MOTO);
        Veiculo vei10 = new Veiculo(10, "DAFRA", "APACHE RTR 200", "GRAFITE", "GJU2609", TipoVeiculo.MOTO);
        lista.addAll(Arrays.asList(vei01, vei02, vei03, vei04, vei05, vei06, vei07, vei08, vei09, vei10));
        return lista;
    }

}