package com.sbs.estacionamento.pocmvc.service;

import com.sbs.estacionamento.pocmvc.PocMvcApplicationTests;
import com.sbs.estacionamento.pocmvc.dto.VeiculoDto;
import com.sbs.estacionamento.pocmvc.entities.Veiculo;
import com.sbs.estacionamento.pocmvc.entities.enums.TipoVeiculo;
import com.sbs.estacionamento.pocmvc.exceptions.ErroVeiculoFormDto;
import com.sbs.estacionamento.pocmvc.exceptions.VeiculoDataIntegrityViolationException;
import com.sbs.estacionamento.pocmvc.exceptions.VeiculoNotFoundException;
import com.sbs.estacionamento.pocmvc.form.EditaVeiculoForm;
import com.sbs.estacionamento.pocmvc.form.VeiculoForm;
import com.sbs.estacionamento.pocmvc.repo.VeiculoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VeiculoServiceTest extends PocMvcApplicationTests {



    @Autowired
    private VeiculoService veiculoService;

    @Mock
    private VeiculoRepository veiculoRepoMock;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
        List<Veiculo> veiculos = veiculos();
        this.veiculoService = new VeiculoService(veiculoRepoMock);
    }

    @Test
    public  void deveRetornarUmaListaDeVeiculosTest(){
        List<Veiculo> veiculos = veiculoRepoMock.findAll();
        Assert.assertTrue(veiculos.isEmpty());
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
        Assert.assertThrows(VeiculoNotFoundException.class, () -> veiculoService.findById(40));
    }

    @Test
    public void deveLancarExcecaoAoTentarDeletarVeiculoPorIdEOVeiculoJaNaoExistirTest(){
        Integer id = 31;
        Assert.assertThrows(VeiculoNotFoundException.class, () -> veiculoService.delete(id));
    }

    @Test
    public void deveInserirNovoVeiculoTest(){
        VeiculoForm veiculo = new VeiculoForm();
        veiculo.setMarca("FERRARI");
        veiculo.setModelo("355 GTS TARGA");
        veiculo.setCor("CINZA");
        veiculo.setPlaca("BIM6140");
        veiculo.setTipo(TipoVeiculo.CARRO);
        Veiculo obj = veiculoService.insert(VeiculoDto.dtoFromVeiculo(veiculo));
        Assert.assertNotNull(obj);
        Assert.assertEquals(obj.getPlaca(), veiculo.getPlaca());
    }

    @Test
    public void deveRetornarExcecaoCasoPlacaDeVeiculoParaInserirJaExistaTest(){
        VeiculoForm veiculo = new VeiculoForm();
        veiculo.setMarca("FERRARI");
        veiculo.setModelo("355 GTS TARGA");
        veiculo.setCor("CINZA");
        veiculo.setPlaca("EIL6488");
        veiculo.setTipo(TipoVeiculo.CARRO);
        Veiculo obj = VeiculoDto.dtoFromVeiculo(veiculo);
        Assert.assertThrows(VeiculoDataIntegrityViolationException.class, () -> veiculoService.insert(obj));
    }

    @Test
    public void deveRetornarExcecaoCasoPlacaDeVeiculoParaAlterarJaExistaTest(){
        Integer id = 10;
        EditaVeiculoForm form = new EditaVeiculoForm();
        form.setCor("VIOLETA");
        form.setPlaca("GAF7153");
        Assert.assertThrows(VeiculoDataIntegrityViolationException.class, () -> veiculoService.editaVeiculo(form, id));
    }

    @Test
    public  void deveEditarVeiculoPorIdTest(){
        Integer id = 10;
        EditaVeiculoForm form = new EditaVeiculoForm();
        form.setCor("ROXA");
        form.setPlaca("KED9708");
        Veiculo veiculo = veiculoService.editaVeiculo(form, id);
        Assert.assertEquals("ROXA", veiculo.getCor());
        Assert.assertEquals("KED9708", veiculo.getPlaca());
    }

    /* TESTES DAS VALIDAÇÕES */

   @Test
    public void deveLancarExcecaoParaCampoMarcaNuloOuBrancoParaNovoVeiculo(){
       VeiculoForm veiculo = new VeiculoForm();
       veiculo.setMarca("F");
       veiculo.setModelo("355 GTS TARGA");
       veiculo.setCor("CINZA");
       veiculo.setPlaca("EIL6488");
       veiculo.setTipo(TipoVeiculo.CARRO);
       Assert.assertThrows(MethodArgumentNotValidException.class, () -> VeiculoDto.dtoFromVeiculo(veiculo));
   }

   private List<Veiculo> veiculos(){
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
       List<Veiculo> lista = Arrays.asList(vei01, vei02, vei03, vei04, vei05, vei06, vei07, vei08, vei09, vei10);

       return lista;
   }
}