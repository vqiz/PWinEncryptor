/**
 * Copyright (c) 2024 by vqiz
 * All rights reserved.
 */

package org.vqiz.api;

import java.util.ArrayList;

public class EventManager {
    private ArrayList<EncryptionEvent> events = new ArrayList<>();
    public static EventManager getfreeinstnace(){
        return new EventManager();
    }
    public void Trigger(EventType eventType){
        events.forEach(encryptionEvent -> {
            if (encryptionEvent.get_eventType().equals(eventType)){
                encryptionEvent.task();
            }
        });


    }
    public void addEvent(EncryptionEvent encryptionEvent){
        events.add(encryptionEvent);
    }
    public void removeEvents(EncryptionEvent encryptionEvent){
        events.remove(encryptionEvent);
    }
    public ArrayList<EncryptionEvent> getEvents() {
        return events;
    }
}
