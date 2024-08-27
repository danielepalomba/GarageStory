package com.app.util;

import com.app.entity.Garage;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOSave {

    public static void save(Garage garage) {
        try {
            Path localFilePath = Paths.get("savings/garage.dat");
            Path parentDir = localFilePath.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(localFilePath.toString()))) {
                oos.writeObject(garage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Garage read() {
        Garage garage = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savings/garage.dat"))) {
            garage = (Garage) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (garage == null) {
            garage = new Garage();
        }
        return garage;
    }

    public static void deleteDirectory() {
        Path path = Paths.get("savings");
        if (Files.exists(path)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                for (Path entry : stream) {
                    if (Files.isDirectory(entry)) {
                        deleteDirectory(entry);
                    } else {
                        Files.delete(entry);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void deleteDirectory(Path path) {
        if (Files.exists(path)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                for (Path entry : stream) {
                    if (Files.isDirectory(entry)) {
                        deleteDirectory(entry);
                    } else {
                        Files.delete(entry);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}