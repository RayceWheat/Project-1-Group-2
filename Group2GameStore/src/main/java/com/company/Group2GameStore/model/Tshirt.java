package com.company.Group2GameStore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="tshirt")
public class Tshirt {
    @Id
    @Column(name="t_shirt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="t_shirt")
    private String tshirt;
    private String color;
    private String size;

    private String description;
    private BigDecimal price;
    private Integer quantity;

    public Tshirt(Integer id, String tshirt, String color, String size, String description, BigDecimal price, Integer quantity) {
        this.id = id;
        this.tshirt = tshirt;
        this.color = color;
        this.size = size;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Tshirt(Integer id, String tshirt, String color, String size) {
        this.id = id;
        this.tshirt = tshirt;
        this.color = color;
        this.size = size;
    }

    public Tshirt() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTshirt() {
        return tshirt;
    }

    public void setTshirt(String tshirt) {
        this.tshirt = tshirt;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tshirt tshirt1 = (Tshirt) o;
        return Objects.equals(id, tshirt1.id) && Objects.equals(tshirt, tshirt1.tshirt) && Objects.equals(color, tshirt1.color) && Objects.equals(size, tshirt1.size) && Objects.equals(description, tshirt1.description) && Objects.equals(price, tshirt1.price) && Objects.equals(quantity, tshirt1.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tshirt, color, size, description, price, quantity);
    }

    @Override
    public String toString() {
        return "Tshirt{" +
                "id=" + id +
                ", tshirt='" + tshirt + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
