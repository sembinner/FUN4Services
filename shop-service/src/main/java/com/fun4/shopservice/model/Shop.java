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
    private String name;

    @Column()
    private String description;

    @Column(updatable = false)
    private int userId;

    @Column(updatable = false)
    private boolean personal;

    public Shop(String name, String description, int userId, boolean personal){
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.personal = personal;
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

    public boolean isPersonal() {
        return personal;
    }

    public void setPersonal(boolean personal) {
        this.personal = personal;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
