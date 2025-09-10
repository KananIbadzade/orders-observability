package com.example.orders.model;

public class Order {
    private String item;
    private int qty;
    private double priceUsd;

    // Getters and setters
    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public double getPriceUsd() { return priceUsd; }
    public void setPriceUsd(double priceUsd) { this.priceUsd = priceUsd; }
}
