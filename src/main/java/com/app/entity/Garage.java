package com.app.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class Garage implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    private HashSet<Bike> bikes;

    public Garage() {
        bikes = new HashSet<>();
    }

    public void addBike(Bike bike) {
        bikes.add(bike);
    }

    public boolean removeBikeByVIN(String vin) {
        return bikes.stream().filter(b-> b.getVIN().equals(vin)).findFirst().map(bikes::remove).orElse(false);
    }

    public Bike getBikeByName(String model) {
        Optional<Bike> optional = bikes.stream().filter(b-> b.getModel().equals(model)).findFirst();
        return optional.orElse(null);
    }

    public HashSet<Bike> getBikes() {
        return bikes;
    }

    public List<Bike> checkInsurance(){
        LocalDate now = LocalDate.now(ZoneId.of("Europe/Rome"));
        return bikes.stream().filter(b-> b.getInsuranceDeadline().isEqual(now) || now.isAfter(b.getInsuranceDeadline())).collect(Collectors.toList());
    }

    public List<Bike> checkReview(){
        LocalDate now = LocalDate.now(ZoneId.of("Europe/Rome"));
        return bikes.stream().filter(b-> b.getReviewDeadline().isEqual(now) || now.isAfter(b.getReviewDeadline())).collect(Collectors.toList());
    }

    public List<Bike> checkTax(){
        LocalDate now = LocalDate.now(ZoneId.of("Europe/Rome"));
        return bikes.stream().filter(b-> b.getTaxDeadline().isEqual(now) || now.isAfter(b.getTaxDeadline())).collect(Collectors.toList());
    }

    public void setBikes(HashSet<Bike> bikes) {
        this.bikes = bikes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Garage Details:\n");

        if (bikes.isEmpty()) {
            sb.append("  No bikes in the garage.\n");
        } else {
            sb.append("Bikes:\n");
            int i = 1;
            for (Bike bike : bikes) {
                sb.append("     ").append("(").append(i).append(") ").append(bike.toString()).append("\n");
                i++;
            }
        }

        return sb.toString();
    }

}
