package com.app.util;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class S3IOService {

    private static final String bucketName = "myawsgarage";
    private static final String keyName = "garage.dat";
    private static final String filePath = "savings/garage.dat";

    public static void uploadFile(S3Client s3) {
        try {
            Path localFilePath = Paths.get(filePath);
            Path parentDir = localFilePath.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }
            s3.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(keyName)
                            .build(),
                    localFilePath); //upload the file to the specified path
            System.out.println("File uploaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void downloadFile(S3Client s3) {
        try {
            Path localFilePath = Paths.get(filePath);
            Path parentDir = localFilePath.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }
            if (Files.exists(localFilePath)) {
                Files.delete(localFilePath);
            }
            s3.getObject(GetObjectRequest.builder()
                            .bucket(bucketName)
                            .key(keyName)
                            .build(),
                    localFilePath);
            System.out.println("File downloaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}