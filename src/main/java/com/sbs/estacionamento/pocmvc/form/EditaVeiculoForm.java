package com.sbs.estacionamento.pocmvc.form;

import com.sbs.estacionamento.pocmvc.entities.Veiculo;
import com.sbs.estacionamento.pocmvc.entities.enums.TipoVeiculo;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EditaVeiculoForm {

    @NotEmpty(message = "cor não pode ser nula ou vazia!")
    @Length(min = 3, max = 15)
    private String cor;

    @Pattern(regexp = "[A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{2}|[A-Z]{3}[0-9]{4}",
            message = "placa deve ter o padrao brasileiro ou mercosul!")
    @NotNull(message = "placa não pode ser nula ou vazia!")
    private String placa;

    public EditaVeiculoForm() {

    }

    public static Veiculo formToVeiculo(EditaVeiculoForm objForm){
        Veiculo veiculo = new Veiculo(objForm.getCor(), objForm.getPlaca());
        return veiculo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
