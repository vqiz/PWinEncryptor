/**
 * Copyright (c) 2024 by vqiz
 * All rights reserved.
 */

package org.vqiz.logging;

public enum LogColor {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    CYAN("\u001B[36m");

    private final String code;

    LogColor(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
