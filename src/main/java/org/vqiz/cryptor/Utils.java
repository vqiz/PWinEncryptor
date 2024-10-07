/**
 * Copyright (c) 2024 by vqiz
 * All rights reserved.
 */

package org.vqiz.cryptor;

import org.vqiz.logging.LogColor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

public class Utils {
    public static Logger logger = Logger.getLogger(Utils.class.getName());
    public static String convertListToString(List<Character> characterList) {
        StringBuilder stringBuilder = new StringBuilder();


        for (Character c : characterList) {
            stringBuilder.append(c);
        }


        return stringBuilder.toString();
    }
    public static void writeToFile(String filePath, String content) {
        Path path = Paths.get(filePath);

        try {
            Files.write(path, content.getBytes());
        } catch (IOException e) {
            logger.severe(LogColor.RED + "Error writing file content" + e.getMessage());
        }
    }
}
