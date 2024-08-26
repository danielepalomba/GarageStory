package com.app;

import com.app.entity.*;
import com.app.util.IOSave;
import com.app.util.S3IOService;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void showMenu() {
        System.out.println("--------------------");
        System.out.println("Add a new bike [1]\nFind a bike [2]\nRemove a bike [3]\nShow all bikes [4]\nExit [5]");
        System.out.println("--------------------");
    }

    public static void bikeMenu() {
        System.out.println("--------------------");
        System.out.println("Add maintenance report [1]\nAdd powerpart [2]\nExit [3]");
        System.out.println("--------------------");
    }

    public static void main(String[] args) {

        S3Client s3 = S3Client.builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();

        S3IOService.downloadFile(s3);

        Garage garage = IOSave.read();
        Scanner scn = new Scanner(System.in);

        char risp = 'y';
        while (risp != 'n') {
            Main.showMenu();
            int choice = scn.nextInt();
            scn.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter the brand: ");
                    String brand = scn.nextLine();
                    System.out.println("Enter the model: ");
                    String model = scn.nextLine();
                    System.out.println("Enter the plate: ");
                    String plate = scn.nextLine();
                    System.out.println("Enter the VIN: ");
                    String vin = scn.nextLine();
                    System.out.println("Enter the registration date: ");
                    String regDate = scn.nextLine();
                    System.out.println("Enter the last revision date: ");
                    String revDate = scn.nextLine();
                    garage.addBike(new Bike(brand, model, plate, vin, LocalDate.parse(regDate), LocalDate.parse(revDate)));
                    break;
                case 2:
                    System.out.println("Enter the Name: ");
                    String NameToFind = scn.nextLine();
                    Bike bike = garage.getBikeByName(NameToFind);
                    if (bike == null) {
                        System.out.println("****Bike not found****");
                        break;
                    }
                    System.out.println(bike);
                    System.out.println("--------------------");
                    char risp2 = 'y';
                    while (risp2 != 'n') {
                        Main.bikeMenu();
                        int choice2 = scn.nextInt();
                        scn.nextLine();
                        switch (choice2) {
                            case 1:
                                System.out.println("Enter the kilometers: ");
                                int kilometers = scn.nextInt();
                                scn.nextLine();
                                System.out.println("Enter the operations: ");
                                String operations = scn.nextLine();
                                System.out.println("Enter the price: ");
                                Double price = scn.nextDouble();
                                scn.nextLine();
                                System.out.println("Enter the date: ");
                                String date = scn.nextLine();
                                Maintenance maintenance = new Maintenance(kilometers, operations, price, LocalDate.parse(date));
                                bike.addMaintenance(maintenance);
                                break;
                            case 2:
                                System.out.println("Enter the name: ");
                                String name = scn.nextLine();
                                System.out.println("Enter the brand: ");
                                String brand2 = scn.nextLine();
                                System.out.println("Enter the price: ");
                                Double price2 = scn.nextDouble();
                                scn.nextLine();
                                Powerpart powerpart = new Powerpart(name, brand2, price2);
                                bike.addPowerpart(powerpart);
                                break;
                            case 3:
                                risp2 = 'n';
                                break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Enter the VIN: ");
                    String vinToRemove = scn.nextLine();
                    garage.removeBikeByVIN(vinToRemove);
                    break;
                case 4:
                    System.out.println("Bikes in the garage: ");
                    System.out.println(garage.getBikes());
                    System.out.println("--------------------");
                    break;
                case 5:
                    risp = 'n';
                    break;
            }
        }
        scn.close();
        IOSave.save(garage);
        S3IOService.uploadFile(s3);
    }
}