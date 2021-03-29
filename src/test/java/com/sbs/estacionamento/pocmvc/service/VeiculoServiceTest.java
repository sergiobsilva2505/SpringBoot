package com.sbs.estacionamento.pocmvc.service;

import com.sbs.estacionamento.pocmvc.PocMvcApplicationTests;
import com.sbs.estacionamento.pocmvc.dto.VeiculoDto;
import com.sbs.estacionamento.pocmvc.entities.Veiculo;
import com.sbs.estacionamento.pocmvc.entities.enums.TipoVeiculo;
import com.sbs.estacionamento.pocmvc.exceptions.VeiculoDataIntegrityViolationException;
import com.sbs.estacionamento.pocmvc.exceptions.VeiculoNotFoundException;
import com.sbs.estacionamento.pocmvc.form.EditaVeiculoForm;
import com.sbs.estacionamento.pocmvc.form.VeiculoForm;
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
        Assert.assertThrows(VeiculoNotFoundException.class, () -> veiculoService.findById(40));
    }

    @Test
    public void deveLancarExcecaoAoTentarDeletarVeiculoPorIdEOVeiculoJaNaoExistirTest(){
        Integer id = 31;
        Assert.assertThrows(VeiculoNotFoundException.class, () -> veiculoService.delete(id));
    }

    @Test
    public void deveInserirNovoVeiculoTest(){
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca("FERRARI");
        veiculo.setModelo("355 GTS TARGA");
        veiculo.setCor("CINZA");
        veiculo.setPlaca("BIM6140");
        veiculo.setTipo(TipoVeiculo.CARRO);
        Veiculo obj = veiculoService.insert(veiculo);
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
    public  void deveEditarVeiculoPorId(){
        Integer id = 10;
        EditaVeiculoForm form = new EditaVeiculoForm();
        form.setCor("ROXA");
        form.setPlaca("KED9708");
        Veiculo veiculo = veiculoService.editaVeiculo(form, id);
        Assert.assertEquals("ROXA", veiculo.getCor());
        Assert.assertEquals("KED9708", veiculo.getPlaca());
    }

    /* TESTES DAS VALIDAÇÕES */

   
}