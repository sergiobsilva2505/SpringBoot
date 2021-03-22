package com.sbs.estacionamento.pocmvc.service;

import com.sbs.estacionamento.pocmvc.dto.NovoVeiculoDto;
import com.sbs.estacionamento.pocmvc.entities.Veiculo;

import com.sbs.estacionamento.pocmvc.repo.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return obj.orElseThrow(() -> new NullPointerException(
                "Objeto não encontrado! Id: "+ id +", Tipo: " + Veiculo.class.getName())); // to do
    }

    public Veiculo insert(Veiculo obj) {
        Veiculo veiculo = veiculoRepository.save(obj);
        return veiculo;
    }

    public Veiculo dtoFromVeiculo(NovoVeiculoDto objDto){
        Veiculo veiculo = new Veiculo(
                null,
                objDto.getMarca(),
                objDto.getModelo(),
                objDto.getCor(),
                objDto.getPlaca(),
                objDto.getTipo());
        return veiculo;
    }

    public Veiculo update(Veiculo obj) {
        Veiculo newObj = findById(obj.getId());
        updateData(newObj, obj);
        return veiculoRepository.save(newObj);
    }

    private void updateData(Veiculo newObj, Veiculo obj) {
        newObj.setPlaca(obj.getPlaca());
    }

    public void delete(Integer id) {
    }
}
