package org.vqiz.cryptor;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

import static java.security.KeyPairGenerator.getInstance;

public class AsyncCryptor {

    PublicKey publicKey;
    PrivateKey privateKey;
    // Schlüsselpaar generieren
    public AsyncCryptor() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator;
        keyPairGenerator = getInstance("RSA");
        keyPairGenerator.initialize(2048); // Schlüssellänge
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();

    }
    public byte[] e(String Text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // Nachricht verschlüsseln
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedMessage = encryptCipher.doFinal(Text.getBytes());
        return encryptedMessage;
    }
    public String encrypt(String text) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String encryptedMessageBase64 = Base64.getEncoder().encodeToString(e(text));
        return encryptedMessageBase64 + " key : " + privateKeyToString(privateKey);
    }

    public String d(String text, String key) throws Exception {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, stringToPrivateKey(key));
        byte[] decryptedMessage = decryptCipher.doFinal(Base64.getDecoder().decode(text.getBytes()));
        String decryptedMessageString = new String(decryptedMessage);
        return decryptedMessageString;
    }

    // Convert PublicKey to String
    public static String publicKeyToString(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    // Convert PrivateKey to String
    public static String privateKeyToString(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    // Convert String to PublicKey
    public static PublicKey stringToPublicKey(String key) throws Exception {
        byte[] byteKey = Base64.getDecoder().decode(key);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(byteKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA"); // Change algorithm if needed
        return keyFactory.generatePublic(spec);
    }

    // Convert String to PrivateKey
    public static PrivateKey stringToPrivateKey(String key) throws Exception {
        byte[] byteKey = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(byteKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA"); // Change algorithm if needed
        return keyFactory.generatePrivate(spec);
    }






}
