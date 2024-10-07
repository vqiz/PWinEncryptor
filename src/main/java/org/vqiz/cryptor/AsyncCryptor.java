/**
 * Copyright (c) 2024 by vqiz
 * All rights reserved.
 */

package org.vqiz.cryptor;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

public class AsyncCryptor {
    private BigInteger n;  // Modul
    private BigInteger e;  // Öffentlicher Exponent
    private BigInteger d;  // Privater Exponent
    private SecureRandom random;
    public static AsyncCryptor getfreeinstance(int bitLength){
        return new AsyncCryptor(bitLength);
    }
    public AsyncCryptor(int bitLength) {
        random = new SecureRandom();
        // Generiere zwei große Primzahlen p und q
        BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);

        n = p.multiply(q);  // n = p * q
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));  // φ(n)

        e = BigInteger.valueOf(65537);  // Wähle e, typischerweise 65537
        d = e.modInverse(phi);  // Berechne d, den modularen Inversen von e
    }

    private byte[] padMessage(byte[] message) {
        // Berechne die benötigte Länge für das Padding
        int paddingLength = n.bitLength() / 8 - message.length - 3;
        if (paddingLength < 8) {
            throw new IllegalArgumentException("Nachricht zu groß für das Padding-Schema");
        }

        // Erstelle ein neues gepaddetes Array mit der entsprechenden Länge
        byte[] padded = new byte[paddingLength + 3 + message.length];

        // Setze die ersten Bytes für das Padding-Schema
        padded[0] = 0; // 0x00
        padded[1] = 2; // 0x02

        // Fülle das Padding mit zufälligen Bytes
        Arrays.fill(padded, 2, padded.length - message.length - 1, (byte) (random.nextInt(255) + 1));

        // Setze das Trennzeichen
        padded[padded.length - message.length - 1] = 0; // 0x00 als Trennzeichen

        // Füge die ursprüngliche Nachricht hinzu
        System.arraycopy(message, 0, padded, padded.length - message.length, message.length);
        return padded;
    }

    public BigInteger encrypt(String message) {
        byte[] paddedMessage = padMessage(message.getBytes()); // Pad die Nachricht
        BigInteger m = new BigInteger(1, paddedMessage); // Konvertiere die gepaddete Nachricht in BigInteger
        return m.modPow(e, n); // c = m^e mod n
    }

    public String decrypt(BigInteger cipherText) {
        BigInteger m = cipherText.modPow(d, n); // m = c^d mod n
        byte[] paddedMessage = m.toByteArray(); // Konvertiere BigInteger zurück zu byte[]

        // Prüfe, ob das Padding gültig ist
        if (paddedMessage.length < 2 || paddedMessage[0] != 0 || paddedMessage[1] != 2) {
            throw new IllegalArgumentException("Ungültiges Padding");
        }

        // Suche nach dem Padding-Separator (0x00)
        int paddingIndex = 2;
        while (paddingIndex < paddedMessage.length && paddedMessage[paddingIndex] != 0) {
            paddingIndex++;
        }

        // Extrahiere die ursprüngliche Nachricht
        byte[] messageBytes = Arrays.copyOfRange(paddedMessage, paddingIndex + 1, paddedMessage.length);
        return new String(messageBytes);
    }
}
