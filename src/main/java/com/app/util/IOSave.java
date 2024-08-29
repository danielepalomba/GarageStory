package com.app.util;

import com.app.entity.Garage;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class IOSave {

    public static void save(Garage garage) {
        try {
            Path localFilePath = S3IOService.getTempDir().resolve("garage.dat");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(localFilePath.toString()))) {
                oos.writeObject(garage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Garage read() {
        Garage garage = null;
        Path localFilePath = S3IOService.getTempDir().resolve("garage.dat");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(localFilePath.toString()))) {
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
        Path tempDir = S3IOService.getTempDir();
        if (Files.exists(tempDir)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(tempDir)) {
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
                Files.delete(tempDir);
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
