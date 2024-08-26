package com.app.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;

public class Garage implements Serializable {

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
        return "Garage{" + "bikes=" + bikes + '}';
    }
}
