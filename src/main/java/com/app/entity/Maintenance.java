package com.app.entity;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("unused")
public class Maintenance implements Serializable {

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
        return "Maintenance{" +
                "kilometers=" + kilometers +
                ", operations='" + operations + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
