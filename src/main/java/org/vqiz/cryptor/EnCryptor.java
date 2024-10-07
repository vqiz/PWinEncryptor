/**
 * Copyright (c) 2024 by vqiz
 * All rights reserved.
 */

package org.vqiz.cryptor;

import java.util.ArrayList;
import java.util.List;

public class EnCryptor {
    private String text;
    private String key;
    private String decryptionkey;
    private String encryptedText;
    public static EnCryptor getfreeinstance(String text, String key){
        return new EnCryptor(text,key);
    }
    public EnCryptor(String text, String key){
        this.key = key;
        this.text = text;
    }
    public String encryptsync(){
        List<Character> output = new ArrayList<>();
        int length = text.length();
        for (int i = 0; i < length; i++){
            output.add(((char) get_snum(text.charAt(i))));
        }
        return Utils.convertListToString(output);
    }
    private int get_snum(char cha){
        int unicodevalue = ((int) cha);
        return unicodevalue * 1187;
    }

}
