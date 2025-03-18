package br.com.zup.Impostos.dtos;

import br.com.zup.Impostos.enums.TaxType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TaxDTO {
    private String id;
    @NotBlank(message = "Por favor, preencha o nome do imposto.")
    private String nome;
    @NotBlank(message = "Por favor, preencha a descrição do imposto.")
    private String descricao;
    @NotNull(message = "Por favor, preencha a alíquota do imposto.")
    @Positive(message = "A alíquota do imposto deve ser maior que zero")
    private Double aliquota;
    private TaxType taxType;

    public TaxDTO() {
    }

    public TaxDTO(String id, String nome, String descricao, Double aliquota) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.aliquota = aliquota;
    }

    public TaxDTO(String id, String nome, String descricao, Double aliquota, TaxType taxType) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.aliquota = aliquota;
        this.taxType = taxType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getAliquota() {
        return aliquota;
    }

    public void setAliquota(Double aliquota) {
        this.aliquota = aliquota;
    }

    public TaxType getTaxType() {
        return taxType;
    }

    public void setTaxType(TaxType taxType) {
        this.taxType = taxType;
    }
}
