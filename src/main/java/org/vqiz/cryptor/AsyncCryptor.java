package org.vqiz.cryptor;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

public class AsyncCryptor {
    private BigInteger n;  // Modulus
    private BigInteger e;  // Public exponent
    private BigInteger d;  // Private exponent
    private SecureRandom random;

    public static AsyncCryptor getfreeinstance(int bitLength) {
        return new AsyncCryptor(bitLength);
    }

    public AsyncCryptor(int bitLength) {
        random = new SecureRandom();

        // Generate two large prime numbers p and q
        BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);

        n = p.multiply(q);  // n = p * q
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));  // φ(n)

        e = BigInteger.valueOf(65537);  // Standard choice for e
        d = e.modInverse(phi);  // Calculate d, the modular inverse of e
    }

    private byte[] padMessage(byte[] message) {
        int k = n.bitLength() / 8;
        int paddingLength = k - message.length - 3;
        if (paddingLength < 8) {
            throw new IllegalArgumentException("Message too large for padding scheme");
        }

        byte[] padded = new byte[k];
        padded[0] = 0; // 0x00
        padded[1] = 2; // 0x02

        // Fill the padding with non-zero random bytes
        for (int i = 2; i < paddingLength + 2; i++) {
            byte randomByte;
            do {
                randomByte = (byte) (random.nextInt(256) & 0xFF);
            } while (randomByte == 0);
            padded[i] = randomByte;
        }

        // Add the separator
        padded[paddingLength + 2] = 0;

        // Copy the original message at the end
        System.arraycopy(message, 0, padded, k - message.length, message.length);
        return padded;
    }

    public BigInteger encrypt(String message) {
        byte[] paddedMessage = padMessage(message.getBytes()); // Pad the message
        BigInteger m = new BigInteger(1, paddedMessage); // Convert to BigInteger
        return m.modPow(e, n); // c = m^e mod n
    }

    public String decrypt(BigInteger cipherText) {
        BigInteger m = cipherText.modPow(d, n); // m = c^d mod n
        byte[] paddedMessage = m.toByteArray(); // Convert BigInteger back to byte[]

        // Ensure proper padding length by adjusting for leading zeroes if necessary
        int k = n.bitLength() / 8;
        if (paddedMessage.length < k) {
            byte[] temp = new byte[k];
            System.arraycopy(paddedMessage, 0, temp, k - paddedMessage.length, paddedMessage.length);
            paddedMessage = temp;
        }

        // Verify padding scheme
        if (paddedMessage[0] != 0 || paddedMessage[1] != 2) {
            throw new IllegalArgumentException("Invalid padding");
        }

        int paddingIndex = 2;
        while (paddingIndex < paddedMessage.length && paddedMessage[paddingIndex] != 0) {
            paddingIndex++;
        }

        if (paddingIndex == paddedMessage.length) {
            throw new IllegalArgumentException("Separator not found in padding");
        }

        byte[] messageBytes = Arrays.copyOfRange(paddedMessage, paddingIndex + 1, paddedMessage.length);
        return new String(messageBytes);
    }
}
