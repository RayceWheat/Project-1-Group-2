package com.company.Group2GameStore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="invoice")
public class Invoice implements Serializable {
    @Id
    @Column(name="invoice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoiceId;

    //    @NotEmpty
    private String name;

    //    @NotEmpty
    private String street;
//    @NotEmpty
    private String city;
//    @NotEmpty
    private String state;
//    @NotEmpty
    private String zipCode;
//    @NotEmpty
    //can only be Tshirt Game or Console
    private String itemType;

    //    @NotNull
    private Integer quantity;

    //call itemid on processing fees repo
//    @NotNull
    private Integer itemId;
//    @NotNull
    //math
    private BigDecimal unitPrice;


//    @NotNull
    private BigDecimal subtotal;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    @JoinColumn(name = "")
//    @NotNull
    private BigDecimal tax;
//    private Set<Tax> taxes = new HashSet<>();

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    @JoinColumn(name = "")
//    @NotNull
    private BigDecimal processingFee;
    //    private Set<ProcessingFee> processingfees = new HashSet<>();

//    @NotNull
    private BigDecimal total;

    public Invoice(Integer invoiceId, String name, String street, String city, String state, String zipCode, String itemType, Integer quantity, Integer itemId, BigDecimal unitPrice, BigDecimal subtotal, BigDecimal tax, BigDecimal processingFee, BigDecimal total) {
        this.invoiceId = invoiceId;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.itemType = itemType;
        this.quantity = quantity;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
        this.tax = tax;
        this.processingFee = processingFee;
        this.total = total;
    }

    public Invoice(String name, String street, String city, String state, String zipCode, String itemType, Integer quantity, Integer itemId, BigDecimal unitPrice) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.itemType = itemType;
        this.quantity = quantity;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
    }

    public Invoice() {
    }

    public Invoice(Integer invoiceId, String name, String street, String city, String state, String zipCode, String itemType, Integer quantity, Integer itemId, BigDecimal unitPrice) {
        this.invoiceId = invoiceId;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.itemType = itemType;
        this.quantity = quantity;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(invoiceId, invoice.invoiceId) && Objects.equals(name, invoice.name) && Objects.equals(street, invoice.street) && Objects.equals(city, invoice.city) && Objects.equals(state, invoice.state) && Objects.equals(zipCode, invoice.zipCode) && Objects.equals(itemType, invoice.itemType) && Objects.equals(itemId, invoice.itemId) && Objects.equals(unitPrice, invoice.unitPrice) && Objects.equals(quantity, invoice.quantity) && Objects.equals(subtotal, invoice.subtotal) && Objects.equals(tax, invoice.tax) && Objects.equals(processingFee, invoice.processingFee) && Objects.equals(total, invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, name, street, city, state, zipCode, itemType, itemId, unitPrice, quantity, subtotal, tax, processingFee, total);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processingFee=" + processingFee +
                ", total=" + total +
                '}';
    }
}
