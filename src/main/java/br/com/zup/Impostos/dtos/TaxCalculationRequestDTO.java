package br.com.zup.Impostos.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TaxCalculationRequestDTO {
    @NotBlank(message = "Por favor, preencha o id do imposto.")
    private String tipoImpostoId;
    @NotNull(message = "Por favor, preencha o valor base para o calculo do imposto.")
    @Positive(message = "O valor base para o calculo do imposto deve ser maior que zero")
    private Double valorBase;

    public TaxCalculationRequestDTO() {
    }

    public TaxCalculationRequestDTO(String tipoImpostoId, Double valorBase) {
        this.tipoImpostoId = tipoImpostoId;
        this.valorBase = valorBase;
    }

    public String getTipoImpostoId() {
        return tipoImpostoId;
    }

    public void setTipoImpostoId(String tipoImpostoId) {
        this.tipoImpostoId = tipoImpostoId;
    }

    public Double getValorBase() {
        return valorBase;
    }

    public void setValorBase(Double valorBase) {
        this.valorBase = valorBase;
    }
}
