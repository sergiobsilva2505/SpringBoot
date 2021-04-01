package com.sbs.estacionamento.pocmvc.service;

import com.sbs.estacionamento.pocmvc.entities.Veiculo;
import com.sbs.estacionamento.pocmvc.entities.enums.TipoVeiculo;
import com.sbs.estacionamento.pocmvc.exceptions.VeiculoDataIntegrityViolationException;
import com.sbs.estacionamento.pocmvc.exceptions.VeiculoNotFoundException;
import com.sbs.estacionamento.pocmvc.form.EditaVeiculoForm;
import com.sbs.estacionamento.pocmvc.repo.VeiculoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class VeiculoServiceIntegrationTest {

    @Spy
    @InjectMocks
    private VeiculoService veiculoService;

    @Mock
    private VeiculoRepository veiculoRepoMock;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
    }

    /*     ########## TESTES UNITÁRIOS ##########     */
    /* @Test
    public  void deveRetornarUmaListaDeVeiculosVaziaTest(){
        List<Veiculo> lista = new ArrayList<>();
        Mockito.when(veiculoRepoMock.findAll()).thenReturn(lista);
        List<Veiculo> veiculos = veiculoRepoMock.findAll();
        Assert.assertTrue(veiculos.isEmpty());
    }*/

    /* FINDALL */
    @Test
    public  void deveRetornarUmaListaDeVeiculosTest(){
        Veiculo vei01 = new Veiculo(1, "VOLKSWAGEN", "POLO", "PRATA", "ENS2923",
                TipoVeiculo.CARRO);
        Veiculo vei10 = new Veiculo(10, "DAFRA", "APACHE RTR 200", "GRAFITE", "GJU2609",
                TipoVeiculo.MOTO);
        List<Veiculo> lista = veiculos();
        Mockito.when(veiculoRepoMock.findAll()).thenReturn(lista);

        List<Veiculo> objList = veiculoService.findAll();

        Assert.assertFalse(objList.isEmpty());
        Assert.assertEquals(objList.get(0).getId(), vei01.getId());
        Assert.assertEquals(objList.get(9).getPlaca(), vei10.getPlaca());
    }
    /* FIND BY ID */
    @Test
    public void deveRetornarUmVeiculoPorIdTest(){
        Integer id = 6;
        Optional<Veiculo> veiculo = Optional.of(new Veiculo(6, "HONDA", "CG125",
                "VERMELHO","CYC1484", TipoVeiculo.MOTO));
        Mockito.when(veiculoRepoMock.findById(id)).thenReturn(veiculo);

        Veiculo obj = veiculoService.findById(id);
        Assert.assertEquals(obj.getId(), id);
    }

    @Test
    public void deveRetornarUmaExceptionQuandoVeiculoPorIdNãoExistirTest(){
        Integer id = 6;
        Optional<Veiculo> veiculo = Optional.ofNullable(null);
        Mockito.when(veiculoRepoMock.findById(id)).thenReturn(veiculo);

        Assert.assertThrows(VeiculoNotFoundException.class, () -> veiculoService.findById(id));
    }

    /* INSERT */
    @Test
    public void deveRetornarUmaExceptionQuandoVeiculoASerPersistidoJaExistirTest(){
        Veiculo veiculo = new Veiculo(6, "HONDA", "CG125", "VERMELHO", "CYC1484",
                TipoVeiculo.MOTO);
        Mockito.when(veiculoRepoMock.findByPlaca(veiculo.getPlaca())).thenReturn(veiculo);

        Assert.assertThrows(VeiculoDataIntegrityViolationException.class, () -> veiculoService.insert(veiculo));
    }

    @Test
    public void devePersistirVeiculoTest(){
        Veiculo veiculo = new Veiculo(6, "HONDA", "CG125", "VERMELHO", "CYC1484",
                TipoVeiculo.MOTO);
        Mockito.when(veiculoRepoMock.findByPlaca(veiculo.getPlaca())).thenReturn(null);
        Mockito.when(veiculoRepoMock.save(veiculo)).thenReturn(veiculo);

        Veiculo obj = veiculoService.insert(veiculo);

        Mockito.verify(veiculoRepoMock).save(obj);

    }

    @Test
    public void deveRetornarExcecaoCasoPlacaDeVeiculoParaInserirJaExistaTest(){
        Veiculo vei05 = new Veiculo(5, "CHEVROLET", "MERIVA", "AMARELO", "CYC1484",
                TipoVeiculo.CARRO);
        Mockito.when(veiculoRepoMock.findByPlaca("CYC1484")).thenReturn(vei05);

        Assert.assertThrows(VeiculoDataIntegrityViolationException.class, () -> veiculoService.insert(vei05));
        Mockito.verify(veiculoRepoMock, Mockito.never()).save(vei05);
    }

    /* DELETE */

    @Test
    public void deveDeletarVeiculoPorIdTest(){
        Integer id = 4;
        Veiculo vei04 = new Veiculo(id, "FERRARI", "FF F1", "VERDE",
                "CFK7094", TipoVeiculo.CARRO);
        Mockito.doReturn(vei04).when(veiculoService).findById(ArgumentMatchers.anyInt());
        Mockito.doNothing().when(veiculoRepoMock).delete(ArgumentMatchers.any(Veiculo.class));

        veiculoService.delete(id);

        Mockito.verify(veiculoRepoMock).delete(ArgumentMatchers.any(Veiculo.class));
    }

    @Test
    public void deveRetornarExcecaoQuandoVeiculoASerDeletadoNaoExistir(){
        Integer id = 4;
        Veiculo vei04 = new Veiculo(id, "FERRARI", "FF F1", "VERDE",
                "CFK7094", TipoVeiculo.CARRO);
        Mockito.doReturn(null).when(veiculoService).findById(ArgumentMatchers.isNull());

        Assert.assertThrows(VeiculoNotFoundException.class, () -> veiculoService.delete(id));
        
        Mockito.verify(veiculoRepoMock, Mockito.never()).delete(ArgumentMatchers.any(Veiculo.class));
    }

    /*     ########## FIM DOS TESTES UNITÁRIOS ##########     */

    /*     ########## TESTES DE INTEGRAÇÃO ##########     */

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
        Veiculo vei08 = new Veiculo(8, "SUZUKI", "DK150FI", "AZUL", "BPA8966", TipoVeiculo.MOTO);
        EditaVeiculoForm form = new EditaVeiculoForm();
        form.setCor("ROXA");
        form.setPlaca("KED9708");
        /*Veiculo veiculo =
        Mockito.when(veiculoRepoMock.save(vei08)).thenReturn(vei08);

        Veiculo veiculo = veiculoService.editaVeiculo(form, vei08.getId());
        Assert.assertEquals("ROXA", veiculo.getCor());
        Assert.assertEquals("KED9708", veiculo.getPlaca());*/
    }

    /*     ########## FIM DOS TESTES DE INTEGRAÇÃO ##########     */

    /*     ########## MÉTODOS AUXILIARES ##########     */

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