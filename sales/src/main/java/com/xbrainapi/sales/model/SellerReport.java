package com.xbrainapi.sales.model;


public class SellerReport {

    private String name;
    private int salesTotal;
    private double dailySalesAverage;


    public SellerReport(){

    }

    public SellerReport(String name, int salesTotal, double dailySalesAverage){
        this.name = name;
        this.salesTotal = salesTotal;
        this.dailySalesAverage = dailySalesAverage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalesTotal() {
        return salesTotal;
    }

    public void setSalesTotal(int salesTotal) {
        this.salesTotal = salesTotal;
    }

    public double getDailySalesAverage() {
        return dailySalesAverage;
    }

    public void setDailySalesAverage(double dailySalesAverage) {
        this.dailySalesAverage = dailySalesAverage;
    }
}
