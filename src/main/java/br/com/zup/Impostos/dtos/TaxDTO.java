package br.com.zup.Impostos.dtos;

import br.com.zup.Impostos.enums.TaxType;

public class TaxDTO {
    private String id;
    private String nome;
    private String descricao;
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
