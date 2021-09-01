package com.xbrainapi.sales.model;

import javax.persistence.*;

@Entity
@Table(name = "seller")
public class Seller {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    public Seller(){

    }
    public Seller(String name){
        super();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
