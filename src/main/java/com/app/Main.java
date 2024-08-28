package com.app;

import com.app.entity.*;
import com.app.util.IOSave;
import com.app.util.S3IOService;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    private static S3Client loadClient() {
        Properties properties = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                exit(1);
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String accessKey = properties.getProperty("aws.accessKeyId");
        String secretKey = properties.getProperty("aws.secretKey");

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);
        return S3Client.builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }

    private static void showMenu() {
        System.out.println("--------------------");
        System.out.println("Add a new bike [1]\nFind a bike [2]\nRemove a bike [3]\nShow all bikes [4]\nExit [5]");
        System.out.println("--------------------");
    }

    private static void bikeMenu() {
        System.out.println("--------------------");
        System.out.println("Add maintenance report [1]\nAdd powerpart [2]\nRemove powerpart [3]\nExit [4]");
        System.out.println("--------------------");
    }

    private static void addBike(Scanner scn, Garage garage) {
        System.out.println("Enter the brand: ");
        String brand = scn.nextLine().trim();
        System.out.println("Enter the model: ");
        String model = scn.nextLine().trim();
        System.out.println("Enter the plate: ");
        String plate = scn.nextLine().trim();
        System.out.println("Enter the VIN: ");
        String vin = scn.nextLine().trim();
        System.out.println("Enter the insurance deadline: ");
        String insuranceDate = scn.nextLine().trim();
        System.out.println("Enter the review deadline: ");
        String revDate = scn.nextLine().trim();
        garage.addBike(new Bike(brand, model, plate, vin, LocalDate.parse(insuranceDate), LocalDate.parse(revDate)));
    }

    private static void findBike(Scanner scn, Garage garage) {
        System.out.println("Enter the Name: ");
        String NameToFind = scn.nextLine().trim();
        Bike bike = garage.getBikeByName(NameToFind);
        if (bike == null) {
            System.out.println("****Bike not found****");
        } else {
            System.out.println(bike);
            System.out.println("--------------------");
            char risp = 'y';
            while (risp != 'n') {
                Main.bikeMenu();
                int choice = scn.nextInt();
                scn.nextLine();
                switch (choice) {
                    case 1:
                        Main.addMaintenance(scn, bike);
                        break;
                    case 2:
                        Main.addPowerpart(scn, bike);
                        break;
                    case 3:
                        Main.removePowerpart(scn, bike);
                        break;
                    case 4:
                        risp = 'n';
                        break;
                }
            }
        }
    }

    private static void addMaintenance(Scanner scn, Bike bike) {
        System.out.println("Enter the kilometers: ");
        int kilometers = scn.nextInt();
        scn.nextLine();
        System.out.println("Enter the operations: ");
        String operations = scn.nextLine().trim();
        System.out.println("Enter the price: ");
        Double price = scn.nextDouble();
        scn.nextLine();
        System.out.println("Enter the date: ");
        String date = scn.nextLine().trim();
        Maintenance maintenance = new Maintenance(kilometers, operations, price, LocalDate.parse(date));
        bike.addMaintenance(maintenance);
    }

    private static void addPowerpart(Scanner scn, Bike bike) {
        System.out.println("Enter the name: ");
        String name = scn.nextLine().trim();
        System.out.println("Enter the brand: ");
        String brand = scn.nextLine().trim();
        System.out.println("Enter the price: ");
        Double price = scn.nextDouble();
        scn.nextLine();
        Powerpart powerpart = new Powerpart(brand, name, price);
        bike.addPowerpart(powerpart);
    }

    private static void removePowerpart(Scanner scn, Bike bike) {
        System.out.println("Enter the name: ");
        String nameToRemove = scn.nextLine().trim();
        Powerpart powerpart = bike.findPowerpartByName(nameToRemove);
        bike.removePowerpart(powerpart);
    }

    private static void removeBike(Scanner scn, Garage garage) {
        System.out.println("Enter the VIN: ");
        String vinToRemove = scn.nextLine().trim();
        garage.removeBikeByVIN(vinToRemove);
    }

    private static void showAllBikes(Garage garage) {
        System.out.println(garage.toString());
        System.out.println("--------------------");
    }

    public static void main(String[] args) {
        S3Client s3 = loadClient();
        S3IOService.downloadFile(s3);

        Garage garage = IOSave.read();
        Scanner scn = new Scanner(System.in);

        char risp = 'y';
        try {
            while (risp != 'n') {
                Main.showMenu();
                int choice = scn.nextInt();
                scn.nextLine();

                switch (choice) {
                    case 1:
                        Main.addBike(scn, garage);
                        break;
                    case 2:
                        Main.findBike(scn, garage);
                        break;
                    case 3:
                        Main.removeBike(scn, garage);
                        break;
                    case 4:
                        Main.showAllBikes(garage);
                        break;
                    case 5:
                        risp = 'n';
                        break;
                }
            }
        } finally {
            scn.close();
            IOSave.save(garage);
            S3IOService.uploadFile(s3);
            IOSave.deleteDirectory();
        }
    }
}