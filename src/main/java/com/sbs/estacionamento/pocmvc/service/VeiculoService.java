package com.sbs.estacionamento.pocmvc.service;

import com.sbs.estacionamento.pocmvc.form.EditaVeiculoForm;
import com.sbs.estacionamento.pocmvc.form.VeiculoForm;
import com.sbs.estacionamento.pocmvc.entities.Veiculo;

import com.sbs.estacionamento.pocmvc.repo.VeiculoRepository;
import com.sbs.estacionamento.pocmvc.exceptions.VeiculoDataIntegrityViolationException;
import com.sbs.estacionamento.pocmvc.exceptions.VeiculoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    public VeiculoRepository veiculoRepository;

    /*@Autowired
    public VeiculoService(VeiculoRepository veiculoRepository){
        this.veiculoRepository = veiculoRepository;
    }*/

    public List<Veiculo> findAll(){
        List<Veiculo> list = veiculoRepository.findAll();
        return list;
    }

    /* TESTAR */
    public Veiculo findById(Integer id) {
        Optional<Veiculo> obj = veiculoRepository.findById(id);
        return obj.orElseThrow(() -> new VeiculoNotFoundException(
                "Objeto não encontrado! Id: "+ id +", Tipo: " + (Veiculo.class.getName())));
    }

    /* testar */
    public Veiculo insert(Veiculo obj) {
        Veiculo veiculo = veiculoRepository.findByPlaca(obj.getPlaca());
        if (veiculo != null){
            throw new VeiculoDataIntegrityViolationException(
                    "Já existe um veiculo com a placa " + obj.getPlaca() + " cadastrado!");
        }
        veiculo = veiculoRepository.save(obj);
        return veiculo;
    }

    public void delete(Integer id) {
        Veiculo veiculo =findById(id);
        try {
            veiculoRepository.delete(veiculo);
        }catch ( DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Não é possível excluir");
        }
    }

    public Veiculo editaVeiculo(EditaVeiculoForm objDto, Integer id){
        /* Verifica se já existe um veiculo com a placa informada. Não é o que será alterado */
        Veiculo veiculo = veiculoRepository.findByPlaca(objDto.getPlaca());
        if (veiculo != null){
            throw new VeiculoDataIntegrityViolationException(
                    "Já existe um veiculo com a placa " + objDto.getPlaca() + " cadastrado!");
        }
        /* Faz a busca pelo veiculo que sofrerá alteraçoes e faz essas alterações */
        Veiculo obj = findById(id);
        obj.setCor(objDto.getCor());
        obj.setPlaca(objDto.getPlaca());
        return veiculoRepository.save(obj);
    }

}
