package com.app;

import java.io.IOException;

public class AppLauncher{

    public static void main(String[] args) {
        // Path to JAR file
        String jarPath = "C:\\Users\\Daniele\\IdeaProjects\\GarageStory\\out\\artifacts\\GarageStory_jar\\GarageStory.jar";

        try {
            // Open terminal and launch jar file
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "start", "cmd.exe", "/k", "java -jar \"" + jarPath + "\" & exit");

            // Start the process
            Process process = builder.start();

            // Wait for the process to terminate
            int exitCode = process.waitFor();
            System.out.println("Processo terminato con codice di uscita: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
