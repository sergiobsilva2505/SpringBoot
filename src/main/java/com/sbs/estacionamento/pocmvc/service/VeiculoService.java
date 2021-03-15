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

    public Veiculo insert(NovoVeiculoDto objDto) {
        Veiculo veiculo = dtoFromVeiculo(objDto);
        return veiculoRepository.save(veiculo);
    }

    public Veiculo dtoFromVeiculo(NovoVeiculoDto objDto){
        Veiculo veiculo = new Veiculo();
        veiculo.setId(null);
        veiculo.setMarca(objDto.getMarca());
        veiculo.setModelo(objDto.getModelo());
        veiculo.setCor(objDto.getCor());
        veiculo.setPlaca(objDto.getPlaca());
        veiculo.setTipo(objDto.getTipo());
        return veiculo;
    }
}
