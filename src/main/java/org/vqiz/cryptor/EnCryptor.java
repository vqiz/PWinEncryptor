/**
 * Copyright (c) 2024 by vqiz
 * All rights reserved.
 */

package org.vqiz.cryptor;

import org.vqiz.Main;
import org.vqiz.api.EventManager;
import org.vqiz.api.EventType;

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
        Main.eventManager.Trigger(EventType.PREENCRYPTION_EVENT);
        List<Character> output = new ArrayList<>();
        int length = text.length();
        for (int i = 0; i < length; i++){
            output.add(((char) get_snum(text.charAt(i), key.charAt(i))));
        }
        return Utils.getfreeinstance().convertListToString(output);
    }
    private int get_snum(char cha, char keychar){
        int unicodevalue = ((int) cha);
        int keyvalue = ((int) keychar);
        return unicodevalue * keyvalue;
    }

}
