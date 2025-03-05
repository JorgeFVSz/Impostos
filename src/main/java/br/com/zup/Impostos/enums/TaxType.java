package br.com.zup.Impostos.enums;

public enum TaxType {
    IPI("Imposto sobre Produtos Industrializados"),
    ICMS("Imposto sobre Circulação de Mercadorias e Serviços"),
    ISS("Imposto Sobre Serviços");

    private final String description;

    TaxType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
