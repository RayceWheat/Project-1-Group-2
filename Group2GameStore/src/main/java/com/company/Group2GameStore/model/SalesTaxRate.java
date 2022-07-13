package com.company.Group2GameStore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "sales_tax_rate")
public class SalesTaxRate implements Serializable {

    @Id
    @Column(name = "tax_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taxId;

    @NotEmpty
    private String state;

    @NotNull
    private BigDecimal rate;

    public SalesTaxRate(Integer taxId, String state, BigDecimal rate) {
        this.taxId = taxId;
        this.state = state;
        this.rate = rate;
    }

    public SalesTaxRate() {
    }


    public Integer getTaxId() {
        return taxId;
    }

    public void setTaxId(Integer taxId) {
        this.taxId = taxId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesTaxRate that = (SalesTaxRate) o;
        return Objects.equals(taxId, that.taxId) && Objects.equals(state, that.state) && Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxId, state, rate);
    }

    @Override
    public String toString() {
        return "SalesTaxRate{" +
                "taxId=" + taxId +
                ", state='" + state + '\'' +
                ", rate=" + rate +
                '}';
    }
}
