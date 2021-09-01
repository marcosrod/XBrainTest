package com.xbrainapi.sales.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "sale")
public class Sale {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;


    @Column(name = "date")
    private Date date;


    @Column(name = "value")
    private double value;

    @Column(name = "sellerId")
    private int sellerId;

    @Column(name = "sellerName")
    private String sellerName;

    public Sale() {

    }

    public Sale(Date date, double value, int sellerId, String sellerName) {
        super();
        this.date = date;
        this.value = value;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
