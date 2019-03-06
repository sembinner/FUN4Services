package com.fun4.shopservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private int id;

    @Column()
    @NotBlank
    private String name;

    @Column()
    private String description;

    @Column(updatable = false)
    private int userId;

    public Shop(String name, String description, int userId){
        this.name = name;
        this.description = description;
        this.userId = userId;
    }

    public Shop() {
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

    public void updateShop(String name, String description) {
        if (name != null){ this.name = name;}
        if (description != null){ this.description = description;}
    }
}
