package com.company.Group2GameStore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "processing_fees")
public class ProcessingFees {

    //Only 3
//
//    Consoles: 14.99
//
//    T-shirts: 1.98
//
//    Games: 1.49


    @Id
    @Column(name = "processingFees_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_type")
    private String productType;

    private double fee;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessingFees that = (ProcessingFees) o;
        return id == that.id && Double.compare(that.fee, fee) == 0 && Objects.equals(productType, that.productType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productType, fee);
    }

    @Override
    public String toString() {
        return "ProcessingFees{" +
                "id=" + id +
                ", productType='" + productType + '\'' +
                ", fee=" + fee +
                '}';
    }
}
