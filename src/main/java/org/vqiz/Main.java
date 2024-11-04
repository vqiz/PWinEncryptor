package org.vqiz;

import org.vqiz.api.EventManager;
import org.vqiz.api.Example;
import org.vqiz.cryptor.AsyncCryptor;
import org.vqiz.cryptor.SyncEncryption;
import org.vqiz.logging.LogColor;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class Main {

    public static EventManager eventManager = EventManager.getfreeinstnace();
    public static SyncEncryption syncEncryption = new SyncEncryption();
    public static AsyncCryptor cryptor;

    static {
        try {
            cryptor = new AsyncCryptor();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws Exception {
         printLargeText();
         eventManager.addEvent(new Example());
         if (args.length < 2){printhelp();return;};
         switch (args[0]){
             case "-encryptsync":
                 encryptsync(args[1], args[2]);
                 break;
             case "-decryptsync":
                 decryptsync(args[2], args[1]);
                 break;
             case "-encrypt":
                 encrypt(args[1]);
                 break;
             case "-decrypt":
                 decrypt(args[1], args[2]);
                 break;
         }



    }
    public static void printhelp(){
        System.out.println(LogColor.RED + "You Havent entered a command! ");
        System.out.println(LogColor.YELLOW + "-encrypt <Text> Verschlüsselt Text");
        System.out.println(LogColor.YELLOW + "-decrypt <Text> Entschlüsselt Text ");
        System.out.println(LogColor.YELLOW + "-encryptsync <Text> <Key> Verschlüsselt nur synchron");
        System.out.println(LogColor.YELLOW + "-decryptsync <Text> <key> Entschlüsselt nur synchron");
        System.out.println(LogColor.RESET + " ");

    }
    public static void encrypt(String text) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        System.out.println("Encryptet message " + cryptor.encrypt(text));
    }
    public static void decrypt(String text, String key) throws Exception {
        System.out.println("decryptet message " + cryptor.decrypt(text, key));
    }
    public static void decryptsync(String key, String text) throws Exception {
        String out = syncEncryption.decrypt(text, stringToSecretKey(key));
        System.out.println(LogColor.GREEN + "Der text lautet: " + out + LogColor.RESET);
    }
    public static void encryptsync(String text, String key) throws Exception {
        System.out.println(LogColor.GREEN + "Start encryption");
        String out = syncEncryption.encrypt(text, stringToSecretKey(key));
        System.out.println(LogColor.GREEN +  "Dein verschlüsselter text lautet " + out + LogColor.RESET);

    }
    public static SecretKey stringToSecretKey(String keyString) {
        byte[] keyBytes = keyString.getBytes(StandardCharsets.UTF_8);
        if (keyBytes.length < 16) {
            throw new IllegalArgumentException("Key must be at least 16 bytes long for AES.");
        }
        return new SecretKeySpec(keyBytes, 0, 16, "AES");
    }
    public static void printLargeText() {
        System.out.println(LogColor.RESET);
        String[] P = {
                "PPPPPP ",
                "P    P ",
                "PPPPPP ",
                "P      ",
                "P      "
        };

        String[] W = {
                "W     W ",
                "W  W  W ",
                "W  W  W ",
                " W W W  ",
                "  W W   "
        };

        String[] i = {
                "  I  ",
                "     ",
                "  I  ",
                "  I  ",
                "  I  "
        };

        String[] n = {
                "      ",
                "NN  N ",
                "N N N ",
                "N  NN ",
                "N   N "
        };

        String[] E = {
                "EEEEE ",
                "E     ",
                "EEE   ",
                "E     ",
                "EEEEE "
        };

        String[] n2 = n;

        String[] c = {
                " CCC  ",
                "C     ",
                "C     ",
                "C     ",
                " CCC  "
        };

        String[] r = {
                "RRRRR  ",
                "R   R  ",
                "RRRRR  ",
                "R  R   ",
                "R   R  "
        };

        String[] y = {
                "Y   Y ",
                " Y Y  ",
                "  Y   ",
                "  Y   ",
                "  Y   "
        };

        String[] p2 = P;

        String[] t = {
                "TTTTTT",
                "  TT  ",
                "  TT  ",
                "  TT  ",
                "  TT  "
        };

        String[] o = {
                " OOO  ",
                "O   O ",
                "O   O ",
                "O   O ",
                " OOO  "
        };

        String[] r2 = r;

        String[][] letters = {P, W, i, n, E, n2, c, r, y, p2, t, o, r2};

        for (int line = 0; line < 5; line++) {

            for (String[] letter : letters) {
                System.out.print(letter[line] + " ");
            }
            System.out.println();
        }
    }
}