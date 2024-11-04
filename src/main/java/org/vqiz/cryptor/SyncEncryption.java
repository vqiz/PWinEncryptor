/**
 * Copyright (c) 2024 by vqiz
 * All rights reserved.
 */

package org.vqiz.cryptor;

import org.vqiz.Main;
import org.vqiz.api.EventType;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class SyncEncryption { // Declares a public class named SyncEncryption, which can be accessed from any other class.

    public SyncEncryption() { // Constructor for SyncEncryption class, initializes the object.
    }

    public SecretKey generateKey(int n) throws Exception { // Method to generate a new AES encryption key of specified bit size (n).
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES"); // Gets an instance of a KeyGenerator for AES encryption.
        keyGenerator.init(n); // Initializes the KeyGenerator to generate a key of length n bits.
        return keyGenerator.generateKey(); // Generates and returns a new AES SecretKey.
    }

    public String encrypt(String plainText, SecretKey secretKey) throws Exception { // Method to encrypt a plaintext string using a SecretKey.
        Cipher cipher = Cipher.getInstance("AES"); // Gets an instance of a Cipher for AES encryption.
        Main.eventManager.Trigger(EventType.PREENCRYPTIONSYNC_EVENT);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey); // Initializes the Cipher in ENCRYPT_MODE with the provided SecretKey.
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes()); // Encrypts the plaintext string to bytes
        Main.eventManager.Trigger(EventType.POSTENCRYPTIONSYNC_EVENT);
        return Base64.getEncoder().encodeToString(encryptedBytes); // Encodes the encrypted bytes to a Base64 string and returns it.
    }

    public String decrypt(String encryptedText, SecretKey secretKey) throws Exception { // Method to decrypt an encrypted string using a SecretKey.
        Cipher cipher = Cipher.getInstance("AES"); // Gets an instance of a Cipher for AES decryption.
        cipher.init(Cipher.DECRYPT_MODE, secretKey); // Initializes the Cipher in DECRYPT_MODE with the provided SecretKey.
        Main.eventManager.Trigger(EventType.PREENCRYPTIONSYNC_EVENT);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText); // Decodes the encrypted Base64 string to bytes.
        byte[] decryptedBytes = cipher.doFinal(decodedBytes); // Decrypts the bytes to original plaintext bytes.
        Main.eventManager.Trigger(EventType.POSTENCRYPTIONSYNC_EVENT);
        return new String(decryptedBytes); // Converts decrypted bytes to a string and returns it.
    }
}

