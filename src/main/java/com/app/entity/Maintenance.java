package com.app.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("unused")
public class Maintenance implements Serializable {

    @Serial
    private static final long serialVersionUID = 3L;
    private int kilometers;
    private String operations;
    private Double price;
    private LocalDate date;

    public Maintenance(int kilometers, String operations, Double price, LocalDate date) {
        this.kilometers = kilometers;
        this.operations = operations;
        this.price = price;
        this.date = date;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Maintenance Details:\n");
        sb.append("             ").append("  Kilometers:   ").append(kilometers).append("\n");
        sb.append("             ").append("  Operations:   '").append(operations).append("'\n");
        sb.append("             ").append("  Price:        ").append(price).append("\n");
        sb.append("             ").append("  Date:         ").append(date).append("\n");
        return sb.toString();
    }
}
