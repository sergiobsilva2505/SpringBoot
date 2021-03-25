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

    public List<Veiculo> findAll(){
        List<Veiculo> list = veiculoRepository.findAll();
        return list;
    }

    public Veiculo findById(Integer id) {
        // Optional utilizado para evitar fazer verificação com if != null
        Optional<Veiculo> obj = veiculoRepository.findById(id);
        return obj.orElseThrow(() -> new VeiculoNotFoundException(
                "Objeto não encontrado! Id: "+ id +", Tipo: " + (Veiculo.class.getName())));
    }


    public Veiculo insert(Veiculo obj) {
        Veiculo veiculo = veiculoRepository.findByPlaca(obj.getPlaca());
        if (veiculo != null){
            throw new VeiculoDataIntegrityViolationException(
                    "Já existe um veiculo com a placa " + obj.getPlaca() + " cadastrado!");
        }
        veiculo = veiculoRepository.save(obj);
        return veiculo;
    }

    public Veiculo update(Veiculo obj) {
        Veiculo newObj = findById(obj.getId());
        return veiculoRepository.save(newObj);
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
        Veiculo obj = findById(id);
        obj.setCor(objDto.getCor());
        obj.setPlaca(objDto.getPlaca());
        return obj;
    }

    public Veiculo dtoFromVeiculo(VeiculoForm objDto){
        Veiculo veiculo = new Veiculo(
                null,
                objDto.getMarca(),
                objDto.getModelo(),
                objDto.getCor(),
                objDto.getPlaca(),
                objDto.getTipo());
        return veiculo;
    }




}
