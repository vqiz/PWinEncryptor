/**
 * Copyright (c) 2024 by vqiz
 * All rights reserved.
 */

package org.vqiz.api;

import org.vqiz.logging.LogColor;

import java.util.logging.Logger;

public class Example implements EncryptionEvent{
    Logger logger = Logger.getLogger(Example.class.getName());
    @Override
    public void task() {
        logger.info(LogColor.GREEN + " Example Code für ein PRE-Encryption Event");
    }

    @Override
    public EventType get_eventType() {
        return EventType.PREENCRYPTION_EVENT;
    }
}
