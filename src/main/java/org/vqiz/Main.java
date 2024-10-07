package org.vqiz;

import org.vqiz.api.EventManager;
import org.vqiz.api.Example;
import org.vqiz.cryptor.DeCryptor;
import org.vqiz.cryptor.EnCryptor;
import org.vqiz.cryptor.Utils;
import org.vqiz.logging.LogColor;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Main {

    public static EventManager eventManager = EventManager.getfreeinstnace();
    public static void main(String[] args) throws IOException {
         printLargeText();
         eventManager.addEvent(new Example());
         if (args.length < 2){printhelp();return;};
         switch (args[0]){
             case "-encryptsync":
                 encryptsync(args[1], args[2]);
                 break;
             case "-decryptsync":
                 decryptsync(args[1]);
                 break;

         }



    }
    public static void printhelp(){
        System.out.println(LogColor.RED + "You Havent entered a command! ");
        System.out.println(LogColor.YELLOW + "-encrypt <Text> <Key> Verschlüsselt Text");
        System.out.println(LogColor.YELLOW + "-decrypt <key> Entschlüsselt Text ");
        System.out.println(LogColor.YELLOW + "-encryptsync <Text> <Key> Verschlüsselt nur synchron");
        System.out.println(LogColor.YELLOW + "-decryptsync <Text> <Key> Entschlüsselt nur synchron");
        System.out.println(LogColor.RESET + " ");

    }
    public static void decryptsync(String key){
        String enctext = Utils.getfreeinstance().readFromFile(new File("encsync.txt").getPath());
        String out = DeCryptor.getFreeInstance(enctext, key).decryptSync();
        System.out.println(LogColor.GREEN + "Der text lautet: " + out);
    }
    public static void encryptsync(String text, String key) throws IOException {
        String out = EnCryptor.getfreeinstance(text,key).encryptsync();
        File file = new File("encsync.txt");
        if (file.exists()){
            file.delete();
        }
        file.createNewFile();
        Utils.getfreeinstance().writeToFile(file.getPath(), out);
        System.out.println(LogColor.GREEN +  "Dein Synchron Verschlüsselter Text wurde in die file encsync.txt geschrieben" + LogColor.RESET);

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