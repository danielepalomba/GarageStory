package com.app.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;

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

    public void removeBikeByVIN(String vin) {
        bikes.removeIf(b-> b.getVIN().equals(vin));
    }

    public Bike getBikeByName(String model) {
        Optional<Bike> optional = bikes.stream().filter(b-> b.getModel().equals(model)).findFirst();
        return optional.orElse(null);
    }

    public HashSet<Bike> getBikes() {
        return bikes;
    }

    @SuppressWarnings("unused")
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
