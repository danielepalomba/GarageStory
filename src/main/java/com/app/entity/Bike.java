package com.app.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings("unused")
public class Bike implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String brand;
    private String model;
    private String plate;
    private String VIN;
    private LocalDate insuranceDeadline;
    private LocalDate reviewDeadline;
    private ArrayList<Maintenance> maintenanceList;
    private ArrayList<Powerpart> powerpartList;

    public Bike(String brand, String model, String plate, String VIN, LocalDate insuranceDeadline, LocalDate reviewDeadline) {
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.VIN = VIN;
        this.insuranceDeadline = insuranceDeadline;
        this.reviewDeadline = reviewDeadline;
        this.maintenanceList = new ArrayList<>();
        this.powerpartList = new ArrayList<>();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public LocalDate getInsuranceDeadline() {
        return insuranceDeadline;
    }

    public void setInsuranceDeadline(LocalDate insuranceDeadline) {
        this.insuranceDeadline = insuranceDeadline;
    }

    public LocalDate getReviewDeadline() {
        return reviewDeadline;
    }

    public void setReviewDeadline(LocalDate reviewDeadline) {
        this.reviewDeadline = reviewDeadline;
    }

    public ArrayList<Maintenance> getMaintenanceList() {
        return maintenanceList;
    }

    public void setMaintenanceList(ArrayList<Maintenance> maintenanceList) {
        this.maintenanceList = maintenanceList;
    }

    public void addMaintenance(Maintenance maintenance) {
        maintenanceList.add(maintenance);
    }

    public ArrayList<Powerpart> getPowerpartList() {
        return powerpartList;
    }

    public void addPowerpart(Powerpart powerpart) {
        powerpartList.add(powerpart);
    }

    public void setPowerpartList(ArrayList<Powerpart> powerpartList) {
        this.powerpartList = powerpartList;
    }

    public void removePowerpart(Powerpart powerpart) {
        powerpartList.remove(powerpart);
    }

    public Powerpart findPowerpartByName(String name) {
        return powerpartList.stream().filter(p-> p.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "Bike{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", plate='" + plate + '\'' +
                ", VIN='" + VIN + '\'' +
                ", insuranceDeadline=" + insuranceDeadline +
                ", reviewDeadline=" + reviewDeadline +
                ", maintenanceList=" + maintenanceList +
                ", powerpartList=" + powerpartList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bike bike = (Bike) o;
        return Objects.equals(getVIN(), bike.getVIN());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getVIN());
    }
}
