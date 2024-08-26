package com.app.util;

import com.app.entity.Garage;

import java.io.*;

public class IOSave {

    public static void save(Garage garage) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savings/garage.dat"))) {
            oos.writeObject(garage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Garage read () {
        Garage garage = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savings/garage.dat"))) {
            garage = (Garage) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return garage;
    }
}