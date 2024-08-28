package com.app.entity;

import java.io.Serial;
import java.io.Serializable;

@SuppressWarnings("unused")
public class Powerpart implements Serializable {

    @Serial
    private static final long serialVersionUID = 4L;
    private String brand;
    private String name;
    private Double price;

    public Powerpart(String brand, String name, Double price) {
        this.brand = brand;
        this.name = name;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Powerpart Details:\n");
        sb.append("             ").append("  Brand:   '").append(brand).append("'\n");
        sb.append("             ").append("  Name:    '").append(name).append("'\n");
        sb.append("             ").append("  Price:   ").append(price).append("\n");
        return sb.toString();
    }

}
