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
    private LocalDate taxDeadline;
    private ArrayList<Maintenance> maintenanceList;
    private ArrayList<Powerpart> powerpartList;

    public Bike(String brand, String model, String plate, String VIN, LocalDate insuranceDeadline, LocalDate reviewDeadline, LocalDate taxDeadline) {
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.VIN = VIN;
        this.insuranceDeadline = insuranceDeadline;
        this.reviewDeadline = reviewDeadline;
        this.taxDeadline = taxDeadline;
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

    public LocalDate getTaxDeadline() {
        return taxDeadline;
    }

    public void setTaxDeadline(LocalDate taxDeadline) {
        this.taxDeadline = taxDeadline;
    }

    public Powerpart findPowerpartByName(String name) {
        return powerpartList.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }

    public Double getPowerpartPrice() {
        return powerpartList.stream().mapToDouble(Powerpart::getPrice).sum();
    }

    public Maintenance findMaintenanceByDate(LocalDate date) {
        return maintenanceList.stream().filter(m -> m.getDate().equals(date)).findFirst().orElse(null);
    }

    public void removeMaintenance(Maintenance maintenance) {
        maintenanceList.remove(maintenance);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bike Details:\n");
        sb.append("         ").append("  Brand:     '").append(brand).append("'\n");
        sb.append("         ").append("  Model:     '").append(model).append("'\n");
        sb.append("         ").append("  Plate:     '").append(plate).append("'\n");
        sb.append("         ").append("  VIN:       '").append(VIN).append("'\n");
        sb.append("         ").append("  Insurance expiry:     ").append(insuranceDeadline).append("\n");
        sb.append("         ").append("  Review expiry:        ").append(reviewDeadline).append("\n");
        sb.append("         ").append("  Tax expiry:           ").append(taxDeadline).append("\n");

        sb.append("\n           Maintenance:\n");
        int i = 1;
        for (Maintenance maintenance : maintenanceList) {
            sb.append("             ").append("(").append(i).append(") ").append(maintenance.toString()).append("\n");
            i++;
        }

        sb.append("\n           Powerparts:\n");
        i = 1;
        for (Powerpart powerpart : powerpartList) {
            sb.append("             ").append("(").append(i).append(") ").append(powerpart.toString()).append("\n");
            i++;
        }

        return sb.toString();
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
