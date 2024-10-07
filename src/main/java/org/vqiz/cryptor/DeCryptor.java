/**
 * Copyright (c) 2024 by vqiz
 * All rights reserved.
 */

package org.vqiz.cryptor;

import org.vqiz.Main;
import org.vqiz.api.EventType;

import java.util.ArrayList;
import java.util.List;

public class DeCryptor {
    private String encryptedText;
    private String key;

    public static DeCryptor getFreeInstance(String encryptedText, String key) {
        return new DeCryptor(encryptedText, key);
    }

    public DeCryptor(String encryptedText, String key) {
        this.encryptedText = encryptedText;
        this.key = key;
    }

    public String decryptSync() {
        Main.eventManager.Trigger(EventType.POSTENCRYPTION_EVENT);
        List<Character> output = new ArrayList<>();
        int length = encryptedText.length();
        for (int i = 0; i < length; i++) {
            output.add((char) getSNum(encryptedText.charAt(i), key.charAt(i)));
        }
        return Utils.getfreeinstance().convertListToString(output);
    }

    private int getSNum(char encryptedChar, char keyChar) {
        int encryptedValue = (int) encryptedChar;
        int keyValue = (int) keyChar;
        return encryptedValue / (keyValue * 1187);
    }
}
