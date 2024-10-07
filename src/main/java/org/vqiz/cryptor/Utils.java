/**
 * Copyright (c) 2024 by vqiz
 * All rights reserved.
 */

package org.vqiz.cryptor;

import org.vqiz.logging.LogColor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

public class Utils {
    public static Utils getfreeinstance(){
        return new Utils();
    }
    public Logger logger = Logger.getLogger(Utils.class.getName());
    public  String convertListToString(List<Character> characterList) {
        StringBuilder stringBuilder = new StringBuilder();


        for (Character c : characterList) {
            stringBuilder.append(c);
        }


        return stringBuilder.toString();
    }
    public void writeToFile(String filePath, String content) {
        Path path = Paths.get(filePath);

        try {
            Files.write(path, content.getBytes());
        } catch (IOException e) {
            logger.severe(LogColor.RED + "Error writing file content" + e.getMessage());
        }
    }
    public  String readFromFile(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            logger.severe("Fehler beim Lesen der Datei: " + e.getMessage());
        }

        return content.toString();
    }
}
