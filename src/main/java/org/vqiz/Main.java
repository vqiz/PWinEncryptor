package org.vqiz;

import org.vqiz.api.EventManager;
import org.vqiz.logging.LogColor;

import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class.getName());
    private static EventManager eventManager = EventManager.getfreeinstnace();
    public static void main(String[] args) {
         printLargeText();
         eventManager.
         if(args.length < 2)printhelp();
         switch (args[0]){





         }



    }
    public static void printhelp(){
        System.out.println(LogColor.RED + "You Havent entered a command! ");
        System.out.println(LogColor.YELLOW + "-encrypt <Text> <Key> Verschlüsselt Text");
        System.out.println(LogColor.YELLOW + "-decrypt <Text> <Key> Entschlüsselt Text ");
        System.out.println(LogColor.YELLOW + "-encryptsync <Text> <Key> Verschlüsselt nur synchron");
        System.out.println(LogColor.YELLOW + "-decryptsync <Text> <Key> Entschlüsselt nur synchron");
        System.out.println(LogColor.RESET + " ");

    }
    public static void printLargeText() {
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