package br.com.zup.Impostos.dtos;

public class TaxCalculationRequestDTO {
    private String tipoImpostoId;
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
