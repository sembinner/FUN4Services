package com.fun4.productservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private int id;

    @Column()
    @NotBlank
    private String name;

    @Column()
    private String description;

    @Column(precision = 19, scale = 2)
    private BigDecimal price;

    @Column(updatable = false)
    private int userId;

    public Product(String name, String description, BigDecimal price, int userId){
        this.name = name;
        this.description = description;
        this.price = price;
        this.userId = userId;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void updateProduct(String name, String description, BigDecimal price) {
        if (name != null){ this.name = name;}
        if (description != null){ this.description = description;}
        if (price != null && price != BigDecimal.valueOf(0)){this.price = price;}
    }
}
