package br.com.zup.Impostos.models;

import br.com.zup.Impostos.enums.TaxType;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class Tax {
    @Id
    @UuidGenerator
    private String uuid;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double rate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaxType taxType;

    public Tax() {
    }

    public Tax(String name, String description, Double rate, TaxType taxType) {
        this.name = name;
        this.description = description;
        this.rate = rate;
        this.taxType = taxType;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public TaxType getTaxType() {
        return taxType;
    }

    public void setTaxType(TaxType taxType) {
        this.taxType = taxType;
    }
}
