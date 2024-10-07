/**
 * Copyright (c) 2024 by vqiz
 * All rights reserved.
 */

package org.vqiz.cryptor;

import java.util.ArrayList;
import java.util.List;

public class EnCryptor {
    String text;
    String key;
    String decryptionkey;
    public static EnCryptor getfreeinstance(String text, String key){
        return new EnCryptor(text,key);
    }
    public EnCryptor(String text, String key){
        this.key = key;
        this.text = text;
    }
    private String encryptsync(){
        int snum = get_snum();
        List<Character> output = new ArrayList<>();
        int length = text.length();


    }
    private int get_snum(){
        int i = 0;
        for(int a=0; a< text.length(); a++){
            int unicodevalue = ((int) text.charAt(a));
            i = i + unicodevalue;
        }
        return i * 1000;


    }
}
